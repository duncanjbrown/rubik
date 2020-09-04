(ns rubik.query
  (:require [rubik.cube :refer :all])
  (:gen-class))

(defn get-face
  [face cube]
  (filter (:filter (face-mapping face)) cube))
