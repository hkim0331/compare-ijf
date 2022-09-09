#!/usr/bin/env bb
;;;
;;; https://github.com/hkim0331/compare-ijf.git
;;;
(ns compare-ijf
  (:require
   [babashka.curl :as curl]
   [babashka.fs :as fs]
   [cheshire.core :as json]
   ;; [clojure.java.io :as io]
   [clojure.java.shell :refer [sh]]
   [clojure.string :as str]))

(def ^:private version "0.3.3")

;; keep last competitions in a file.
(def ^:private orig (str (System/getenv "HOME") "/.competitions"))
(def ^:private download "/tmp/from-ijf")

(defn get-url
  [url & [opt]] (curl/get url opt))

(defn get-competitions
  "Fetch competitions JSON from data.ijf.org,
   return it's body as clojure map."
  []
  (-> (get-url "https://data.ijf.org/api/get_json"
               {:query-params {"params[action]" "competition.get_list"}})
      :body
      (json/parse-string true)))

(defn filter-competitions
  "filter map remaining :id_competition, :name, :has_results.
   returns a map."
  [coll]
  (->> coll
       (map #(select-keys % [:id_competition :name :has_results]))
       (sort-by :id_competition)))

(defn save-as-text
  "save coll's values only to dest, as,
   1000 World Cup Cairo 2010 112
   1001 World Cup Belo Horizonte 2010 112
   1002 World Cup Madrid 2010 56
   ..."
  [coll dest]
  (let [lines (atom [])]
    (doseq [s coll]
      (swap! lines conj (str/join "," (vals s))))
    (spit dest (str/join "\n" @lines))
    (spit dest "\n" :append true)))

(defn compare-ijf
  [update?]
  (-> (get-competitions)
      (filter-competitions)
      (save-as-text download))
  (let [{:keys [exit out _]} (sh "diff" "--normal" "--text" orig download)]
    (if (zero? exit)
      (println "no new data")
      (do
        (println out)
        (when update?
          (sh "cp" download orig)
          (println "updated"))))))

(defn has?
  [s]
  (some #(= s %) *command-line-args*))

(defn mtime
  [f]
  (-> (fs/last-modified-time f)
      str))

(defn -main
  []
  (cond
    (has? "--version") (println version)
    (has? "--last") (mtime orig)
    :else (compare-ijf (has? "--update"))))

(-main)
