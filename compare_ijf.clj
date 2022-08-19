#!/usr/bin/env bb
(ns compare-ijf
  (:require
   [babashka.curl :as curl]
   [cheshire.core :as json]
   [clojure.java.io :as io]
   [clojure.java.shell :refer [sh]]
   [clojure.string :as str]))

;; keep last competitions in a file.
(def ^:private competitions (str (System/getenv "HOME") "/.competitions"))
;; compare files on /tmp.
(def ^:private file-a "/tmp/competitions-A")
(def ^:private file-b "/tmp/competitions-B")

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

(comment
  (def ijf (get-competitions)))

(defn filter-competitions
  "filter map remaining :id_competition, :name, :has_results.
   returns a map."
  [coll]
  (->> coll
       (map #(select-keys % [:id_competition :name :has_results]))
       (sort-by :id_competition)))

;;(filter-competitions ijf)

(defn save-as-text
  "save coll's values only, as,
   1000 World Cup Cairo 2010 112
   1001 World Cup Belo Horizonte 2010 112
   1002 World Cup Madrid 2010 56
   ..."
  [coll]
  (let [lines (atom [])]
    (doseq [s coll]
      (swap! lines conj (str/join "," (vals s))))
    (spit competitions (str/join "\n" @lines))
    (spit competitions "\n" :append true)))

;;(save-as-text (filter-competitions ijf))

(defn compare-ijf
  "ダウンロード済みの competitions と新たにダウンロードする competitions を
   diff で比較する。"
  []
  (when (.exists (io/file competitions))
    (sh "cp" competitions file-a))
  (-> (get-competitions)
      (filter-competitions)
      (save-as-text))
  (sh "cp" competitions file-b)
  (let [{:keys [exit out err]} (sh "diff" "--normal" "--text" file-a file-b)]
    (if-not (zero? exit)
      (println out)
      (println err))))

(defn -main
  []
  (let [args *command-line-args*]
     (println args)
     (compare-ijf)))

(-main)
