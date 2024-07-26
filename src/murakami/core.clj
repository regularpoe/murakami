(ns murakami.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn is-markdown-file? [file]
  (str/ends-with? (.getName (io/file file)) ".md"))

(defn read-markdown-files [dir]
  (let [files (filter is-markdown-file? (file-seq (io/file dir)))]
    (doseq [file files]
      (println "Printing content of:" (.getName file))
      (println (slurp file)))))

(defn -main [& args]
  (if-let [dir (first args)]
    (read-markdown-files dir)
    (println "Provide path to Markdown files")))
