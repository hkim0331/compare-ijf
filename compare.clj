#!/usr/bin/env bb
(require '[babashka.curl :as curl]
         '[cheshire.core :as json]
         '[clojure.string :as str])

(def competitions (str (System/getenv "HOME") "/.competitions"))

(defn get-url
  [url & [opt]] (curl/get url opt))

(comment
  (get-url "https://l22.melt.kyutech.ac.jp"))

(defn get-competitions
  "Returns ijf competitions, json format."
  []
  (-> (get-url "https://data.ijf.org/api/get_json"
               {:query-params {"params[action]" "competition.get_list"}})
      :body
      (json/parse-string true)))

(def ijf (get-competitions))

(defn filter-competitions
  [json]
  (->> json
       (map #(select-keys % [:id_competition :name :has_results]))
       (sort-by :id_competition)
       (map vals)))

(filter-competitions ijf)

(defn save-as-text
  [coll]
  (let [lines (atom [])]
    (doseq [s coll]
      (swap! lines conj (str/join " " s)))
    (spit competitions (str/join "\n" @lines))))

(save-as-text (filter-competitions ijf))

(defn load-competitions
  []
  (-> (slurp competitions)
      (json/parse-string true)))

(load-competitions)

(let [ijf (get-competitions)]
  (as-> ijf $
    (map #(select-keys % [:id_competition :name :has_results]) $)
    (sort-by :id_competition $)))