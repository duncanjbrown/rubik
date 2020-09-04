(ns rubik.cube
  (:gen-class))

(def default-cube [
                   ;; Top of cube
                   {:position {:x 0 :y 0 :z 1}
                    :colors {
                             :x nil
                             :y nil
                             :z :white
                             }}
                   {:position {:x -1 :y -1 :z 1}
                    :colors {
                             :y :green
                             :x :orange
                             :z :white
                             }}
                   {:position {:x -1 :y 0 :z 1}
                    :colors {
                             :y nil
                             :x :orange
                             :z :white
                             }}
                   {:position {:x -1 :y 1 :z 1}
                    :colors {
                             :y :blue
                             :x :orange
                             :z :white
                             }}
                   {:position {:x 0 :y 1 :z 1}
                    :colors {
                             :y :blue
                             :x nil
                             :z :white
                             }}
                   {:position {:x 1 :y 1 :z 1}
                    :colors {
                             :y :blue
                             :x :red
                             :z :white
                             }}
                   {:position {:x 1 :y 0 :z 1}
                    :colors {
                             :y nil
                             :x :red
                             :z :white
                             }}
                   {:position {:x 1 :y -1 :z 1}
                    :colors {
                             :y :green
                             :x :red
                             :z :white
                             }}
                   {:position {:x 0 :y -1 :z 1}
                    :colors {
                             :y :green
                             :x nil
                             :z :white
                             }}


                   ;; Middle of cube
                   {:position {:x -1 :y -1 :z 0}
                    :colors {
                             :y :green
                             :x :orange
                             :z nil
                             }}
                   {:position {:x -1 :y 0 :z 0}
                    :colors {
                             :y nil
                             :x :orange
                             :z nil
                             }}
                   {:position {:x -1 :y 1 :z 0}
                    :colors {
                             :y :blue
                             :x :orange
                             :z nil
                             }}
                   {:position {:x 0 :y 1 :z 0}
                    :colors {
                             :y :blue
                             :x nil
                             :z nil
                             }}
                   {:position {:x 1 :y 1 :z 0}
                    :colors {
                             :y :blue
                             :x :red
                             :z nil
                             }}
                   {:position {:x 1 :y 0 :z 0}
                    :colors {
                             :y nil
                             :x :red
                             :z nil
                             }}
                   {:position {:x 1 :y -1 :z 0}
                    :colors {
                             :y :green
                             :x :red
                             :z nil
                             }}
                   {:position {:x 0 :y -1 :z 0}
                    :colors {
                             :y :green
                             :x nil
                             :z nil
                             }}

                   ;; Bottom of cube

                   {:position {:x -1 :y -1 :z -1}
                    :colors {
                             :y :green
                             :x :orange
                             :z :yellow
                             }}
                   {:position {:x -1 :y 0 :z -1}
                    :colors {
                             :y nil
                             :x :orange
                             :z :yellow
                             }}
                   {:position {:x -1 :y 1 :z -1}
                    :colors {
                             :y :blue
                             :x :orange
                             :z :yellow
                             }}
                   {:position {:x 0 :y 1 :z -1}
                    :colors {
                             :y :blue
                             :x nil
                             :z :yellow
                             }}
                   {:position {:x 1 :y 1 :z -1}
                    :colors {
                             :y :blue
                             :x :red
                             :z :yellow
                             }}
                   {:position {:x 1 :y 0 :z -1}
                    :colors {
                             :y nil
                             :x :red
                             :z :yellow
                             }}
                   {:position {:x 1 :y -1 :z -1}
                    :colors {
                             :y :green
                             :x :red
                             :z :yellow
                             }}
                   {:position {:x 0 :y -1 :z -1}
                    :colors {
                             :y :green
                             :x nil
                             :z :yellow
                             }}
                   {:position {:x 0 :y 0 :z -1}
                    :colors {
                             :y nil
                             :x nil
                             :z :yellow
                             }}
                   ])

(defn face-mapping
  [face]
  (face {
         :B {
             :filter #(= 1 (:y (:position %)))
             :clockwise? false
             :dimension :y
             }
         :B' {
              :filter #(= 1 (:y (:position %)))
              :clockwise? true
              :dimension :y
              }
         :F {
             :filter #(= -1 (:y (:position %)))
             :clockwise? true
             :dimension :y
             }
         :F' {
              :filter #(= -1 (:y (:position %)))
              :clockwise? false
              :dimension :y
              }
         :L {
             :filter #(= -1 (:x (:position %)))
             :clockwise? false
             :dimension :x
             }
         :L' {
              :filter #(= -1 (:x (:position %)))
              :clockwise? true
              :dimension :x
              }
         :R {
             :filter #(= 1 (:x (:position %)))
             :clockwise? true
             :dimension :x
             }
         :R' {
              :filter #(= 1 (:x (:position %)))
              :clockwise? false
              :dimension :x
              }
         :U {
             :filter #(= 1 (:z (:position %)))
             :clockwise? true
             :dimension :z
             }
         :U' {
              :filter #(= 1 (:z (:position %)))
              :clockwise? false
              :dimension :z
              }
         :D {
             :filter #(= -1 (:z (:position %)))
             :clockwise? false
             :dimension :z
             }
         :D' {
              :filter #(= -1 (:z (:position %)))
              :clockwise? true
              :dimension :z
              }
         }))
