(ns murakami.core
  (:gen-class)
  (:require [markdown.core :as markdown]
            [selmer.parser :as selmer]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def template "
<!DOCTYPE html>
<html>
<head>
    <title>Markdown to HTML</title>
</head>
<body>
    <h1>Markdown Content</h1>
    <div>
        {{content | safe}}
    </div>
</body>
</html>  
")

(defn is-markdown-file? [file]
  (str/ends-with? (.getName (io/file file)) ".md"))

(defn read-markdown-files [dir]
  (let [files (filter is-markdown-file? (file-seq (io/file dir)))]
    (doseq [file files]
      (println "Rendering HTML content of:" (.getName file))
      (println "----------------------------------------")
      (let [content (slurp file)
            html-content (markdown/md-to-html-string content)
            rendered-html (selmer/render template {:content html-content})]
        (println rendered-html))
      (println "----------------------------------------"))))

(defn -main [& args]
  (if-let [dir (first args)]
    (read-markdown-files dir)
    (println "Provide path to Markdown files")))
