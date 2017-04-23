(ns rubik.core
  (:require [rubik.transform :refer :all]
            [rubik.cube :refer :all]
            [rubik.query :refer :all])
  (:gen-class))

(defn move
  [axis offset cube]
  (let [to-rotate (filter #(= offset (axis (:position %))) cube)]
    (into
      (remove (set to-rotate) cube)
      (map (partial rotate-piece-around-axis axis) to-rotate))))

