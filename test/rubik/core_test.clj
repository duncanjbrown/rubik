(ns rubik.core-test
  (:require [clojure.test :refer :all]
            [rubik.cube :refer :all]
            [rubik.core :refer :all]))

(deftest query-test
  (testing "get-face"
    (is (= (repeat 3 '(:blue :blue :blue)) (get-face default-cube :N))))
    (is (= (repeat 3 '(:green :green :green)) (get-face default-cube :S))))
    (is (= (repeat 3 '(:orange :orange :orange)) (get-face default-cube :E)))
    (is (= (repeat 3 '(:red :red :red)) (get-face default-cube :W)))
    (is (= (repeat 3 '(:white :white :white)) (get-face default-cube :U)))
    (is (= (repeat 3 '(:yellow :yellow :yellow)) (get-face default-cube :D)))

(deftest transform-test
  (testing "rotate-90"
    (is (= [-1 1] (rotate-90 [1 1])))
    (is (= [-1 -1] (rotate-90 [-1 1])))
    (is (= [1 -1] (rotate-90 [-1 -1])))
    (is (= [1 1] (rotate-90 [1 -1]))))

  (testing "swap-faces"
    (is (=
          {:colors
           {:x :red :y :blue}}
          (swap-faces :x :y {:colors
                       {:x :blue :y :red}}))))

  (testing "rotate-piece-around-axis"
    (is (=
         {:colors
          {:x :blue :y :red :z :white}
        :position { :x -1 :y 1 :z 1 }}
         (rotate-piece-around-axis :z { :colors { :x :red :y :blue :z :white }
                                      :position { :x 1 :y 1 :z 1 }}))))

  (testing "rotate-around-z"
    (is (=
          {:position
           {:x -1 :y -1 :z 1}}
          ((rotate-around-axis :z) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 0 :z 1}}
          ((rotate-around-axis :z) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 1}}
          ((rotate-around-axis :z) {:position {:x 1 :y 1 :z 1}}))))

  (testing "rotate-around-y"
    (is (=
          {:position
           {:x -1 :y 1 :z 1}}
          ((rotate-around-axis :y) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 0}}
          ((rotate-around-axis :y) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z -1}}
          ((rotate-around-axis :y) {:position {:x -1 :y 1 :z 1}}))))

  (testing "rotate-around-x"
    (is (=
          {:position
           {:x 1 :y -1 :z 1}}
          ((rotate-around-axis :x) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z -1}}
          ((rotate-around-axis :x) {:position {:x 1 :y -1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z 1}}
          ((rotate-around-axis :x) {:position {:x 1 :y 1 :z 1}}))))
  )
