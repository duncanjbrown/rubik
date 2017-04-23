(ns rubik.core
  (:require [clojure.core.matrix :as m])
  (:gen-class))

(defn rotate-90
  [vctr]
  (vec (map int (m/mmul [[0 -1] [1 0]] vctr))))

(defn swap-faces
  [face1 face2 cubie]
  (-> cubie
      (assoc-in [:colors face1] (get-in cubie [:colors face2]))
      (assoc-in [:colors face2] (get-in cubie [:colors face1]))))

(defn rotate-in-xy-plane
  [cubie]
  (update cubie :position
          (fn [[x y z]]
            (conj (rotate-90 [x y]) z))))

(defn rotate-in-yz-plane
  [cubie]
  (update cubie :position
          (fn [[x y z]]
            (cons x (rotate-90 [y z])))))

(defn rotate-row
  [cubies]
  (map (fn [c]
         (-> c
             (rotate-in-xy-plane)
             ((partial swap-faces :x :y)))) cubies))

(defn rotate-column
  [cubies]
  (map (fn [c]
         (-> c
             (rotate-in-yz-plane)
             ((partial swap-faces :y :z)))) cubies))

(defn rotate-row-n
  [cube n]
  (let [pred #(= n (last (:position %)))]
    (concat 
      (remove pred cube)
      (rotate-row (filter pred cube)))))

(def default-cube [
                   ;; Top of cube
                   {:position [0 0 1]
                    :colors {
                             :x nil
                             :y nil
                             :z :white
                             }}
                   {:position [-1 -1 1]
                    :colors {
                             :y :green
                             :x :orange
                             :z :white
                             }}
                  {:position [-1 0 1]
                   :colors {
                            :y nil
                            :x :orange
                            :z :white
                            }}
                  {:position [-1 1 1]
                   :colors {
                            :y :blue
                            :x :orange
                            :z :white
                            }}
                  {:position [0 1 1]
                   :colors {
                            :y :blue
                            :x nil
                            :z :white
                            }}
                  {:position [1 1 1]
                   :colors {
                            :y :blue
                            :x :red
                            :z :white
                            }}
                  {:position [1 0 1]
                   :colors {
                            :y nil
                            :x :red
                            :z :white
                            }}
                  {:position [1 -1 1]
                   :colors {
                            :y :green
                            :x :red
                            :z :white
                            }}
                  {:position [0 -1 1]
                   :colors {
                            :y :green
                            :x nil
                            :z :white
                            }}


                  ;; Middle of cube
                  {:position [-1 -1 0]
                   :colors {
                            :y :green
                            :x :orange
                            :z nil
                            }}
                  {:position [-1 0 0]
                   :colors {
                            :y nil
                            :x :orange
                            :z nil
                            }}
                  {:position [-1 1 0]
                   :colors {
                            :y :blue
                            :x :orange
                            :z nil
                            }}
                  {:position [0 1 0]
                   :colors {
                            :y :blue
                            :x nil
                            :z nil
                            }}
                  {:position [1 1 0]
                   :colors {
                            :y :blue
                            :x :red
                            :z nil
                            }}
                  {:position [1 0 0]
                   :colors {
                            :y nil
                            :x :red
                            :z nil
                            }}
                  {:position [1 -1 0]
                   :colors {
                            :y :green
                            :x :red
                            :z nil
                            }}
                  {:position [0 -1 0]
                    :colors {
                            :y :green
                            :x nil
                            :z nil
                            }}

                  ;; Bottom of cube

                  {:position [-1 -1 -1]
                   :colors {
                            :y :green
                            :x :orange
                            :z :yellow
                            }}
                 {:position [-1 0 -1]
                  :colors {
                           :y nil
                           :x :orange
                           :z :yellow
                           }}
                 {:position [-1 1 -1]
                  :colors {
                           :y :blue
                           :x :orange
                           :z :yellow
                           }}
                 {:position [0 1 -1]
                  :colors {
                           :y :blue
                           :x nil
                           :z :yellow
                           }}
                 {:position [1 1 -1]
                  :colors {
                           :y :blue
                           :x :red
                           :z :yellow
                           }}
                 {:position [1 0 -1]
                  :colors {
                           :y nil
                           :x :red
                           :z :yellow
                           }}
                 {:position [1 -1 -1]
                  :colors {
                           :y :green
                           :x :red
                           :z :yellow
                           }}
                 {:position [0 -1 -1]
                  :colors {
                           :y :green
                           :x nil
                           :z :yellow
                           }}
                   ])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
