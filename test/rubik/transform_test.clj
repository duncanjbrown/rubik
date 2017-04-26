(ns rubik.transform-test
  (:require [clojure.test :refer :all]
            [rubik.cube :refer :all]
            [rubik.core :refer :all]))

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

  (testing "rotate-piece-around-axis applies rotation and swap-faces"
    (is (=
         {:colors
          {:x :blue :y :red :z :white}
        :position { :x -1 :y 1 :z 1 }}
         (rotate-piece-around-axis :z { :colors { :x :red :y :blue :z :white }
                                      :position { :x 1 :y 1 :z 1 }}))))

  )
