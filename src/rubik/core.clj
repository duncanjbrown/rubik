(ns rubik.core
  (:require [rubik.transform :refer :all]
            [rubik.query :refer :all])
  (:gen-class))

(defn move
  [axis offset cube]
  (let [to-rotate (filter #(= offset (axis (:position %))) cube)]
    (println to-rotate)
    (into
      (remove (set to-rotate) cube)
      (map (partial rotate-piece-around-axis axis) to-rotate))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
