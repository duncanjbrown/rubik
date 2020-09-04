(ns rubik.transform-tests
  (:require [clojure.test :refer :all]
            [rubik.cube :refer :all]
            [rubik.transform :refer :all]))

(deftest rotate-test
  (testing "rotate'"
    (is (= [-1 1] (rotate' [1 1])))
    (is (= [-1 -1] (rotate' [-1 1])))
    (is (= [1 -1] (rotate' [-1 -1])))
    (is (= [1 1] (rotate' [1 -1]))))

  (testing "rotate"
    (is (= [1 -1] (rotate [1 1])))
    (is (= [-1 -1] (rotate [1 -1])))
    (is (= [-1 1] (rotate [-1 -1])))
    (is (= [1 1] (rotate [-1 1]))))

  (testing "rotate rotate rotate = rotate'"
    (is (rotate' [1 1]) (rotate (rotate (rotate [1 1]))))
    (is (rotate' [-1 1]) (rotate (rotate (rotate [-1 1]))))
    (is (rotate' [-1 -1]) (rotate (rotate (rotate [-1 -1]))))
    (is (rotate' [1 -1]) (rotate (rotate (rotate [1 -1])))))

  (testing "rotate' rotate' rotate' = rotate"
    (is (rotate [1 1]) (rotate' (rotate' (rotate' [1 1]))))
    (is (rotate [-1 1]) (rotate' (rotate' (rotate' [-1 1]))))
    (is (rotate [-1 -1]) (rotate' (rotate' (rotate' [-1 -1]))))
    (is (rotate [1 -1]) (rotate' (rotate' (rotate' [1 -1]))))))

(deftest swap-faces-test
  (testing "swap-faces"
    (is (=
          {:colors
           {:x :red :y :blue}}
          (swap-faces :x :y {:colors
                             {:x :blue :y :red}})))))

(deftest rotate-around-axis-test
  (testing "rotate-around-z'"
    (is (=
          {:position
           {:x -1 :y -1 :z 1}}
          ((rotate-around-axis :z false) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 0 :z 1}}
          ((rotate-around-axis :z false) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 1}}
          ((rotate-around-axis :z false) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z -1}}
          ((rotate-around-axis :z false) {:position {:x 1 :y 1 :z -1}})))
    (is (=
          {:position
           {:x -1 :y 0 :z -1}}
          ((rotate-around-axis :z false) {:position {:x 0 :y 1 :z -1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z -1}}
          ((rotate-around-axis :z false) {:position {:x -1 :y 1 :z -1}}))))

  (testing "rotate-around-z"
    (is (=
          {:position
           {:x 1 :y 1 :z 1}}
          ((rotate-around-axis :z true) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y 0 :z 1}}
          ((rotate-around-axis :z true) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z 1}}
          ((rotate-around-axis :z true) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z -1}}
          ((rotate-around-axis :z true) {:position {:x 1 :y 1 :z -1}})))
    (is (=
          {:position
           {:x 1 :y 0 :z -1}}
          ((rotate-around-axis :z true) {:position {:x 0 :y 1 :z -1}})))
    (is (=
          {:position
           {:x 1 :y 1 :z -1}}
          ((rotate-around-axis :z true) {:position {:x -1 :y 1 :z -1}}))))

  (testing "rotate-around-y'"
    (is (=
          {:position
           {:x -1 :y 1 :z 1}}
          ((rotate-around-axis :y false) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 0}}
          ((rotate-around-axis :y false) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z -1}}
          ((rotate-around-axis :y false) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z 1}}
          ((rotate-around-axis :y false) {:position {:x 1 :y -1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z 0}}
          ((rotate-around-axis :y false) {:position {:x 0 :y -1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z -1}}
          ((rotate-around-axis :y false) {:position {:x -1 :y -1 :z 1}}))))

  (testing "rotate-around-y"
    (is (=
          {:position
           {:x 1 :y 1 :z -1}}
          ((rotate-around-axis :y true) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y 1 :z 0}}
          ((rotate-around-axis :y true) {:position {:x 0 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y 1 :z 1}}
          ((rotate-around-axis :y true) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z -1}}
          ((rotate-around-axis :y true) {:position {:x 1 :y -1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z 0}}
          ((rotate-around-axis :y true) {:position {:x 0 :y -1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z 1}}
          ((rotate-around-axis :y true) {:position {:x -1 :y -1 :z 1}}))))

  (testing "rotate-around-x'"
    (is (=
          {:position
           {:x 1 :y -1 :z 1}}
          ((rotate-around-axis :x false) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z 0}}
          ((rotate-around-axis :x false) {:position {:x 1 :y 0 :z 1}})))
    (is (=
          {:position
           {:x 1 :y -1 :z -1}}
          ((rotate-around-axis :x false) {:position {:x 1 :y -1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z 1}}
          ((rotate-around-axis :x false) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z 0}}
          ((rotate-around-axis :x false) {:position {:x -1 :y 0 :z 1}})))
    (is (=
          {:position
           {:x -1 :y -1 :z -1}}
          ((rotate-around-axis :x false) {:position {:x -1 :y -1 :z 1}}))))

  (testing "rotate-around-x"
    (is (=
          {:position
           {:x 1 :y 1 :z -1}}
          ((rotate-around-axis :x true) {:position {:x 1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x 1 :y 1 :z 0}}
          ((rotate-around-axis :x true) {:position {:x 1 :y 0 :z 1}})))
    (is (=
          {:position
           {:x 1 :y 1 :z 1}}
          ((rotate-around-axis :x true) {:position {:x 1 :y -1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z -1}}
          ((rotate-around-axis :x true) {:position {:x -1 :y 1 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 0}}
          ((rotate-around-axis :x true) {:position {:x -1 :y 0 :z 1}})))
    (is (=
          {:position
           {:x -1 :y 1 :z 1}}
          ((rotate-around-axis :x true) {:position {:x -1 :y -1 :z 1}})))))

(deftest rotate-piece-around-axis-test
  (testing "rotate-piece-around-axis :z, counterclockwise, applies rotation and swap-faces"
    (is (=
          {:colors
                     {:x :blue :y :red :z :white}
           :position {:x -1 :y 1 :z 1}}
          (rotate-piece-around-axis :z false {:colors   {:x :red :y :blue :z :white}
                                              :position {:x 1 :y 1 :z 1}}))))

  (testing "rotate-piece-around-axis :z, clockwise, applies rotation and swap-faces"
    (is (=
          {:colors
                     {:x :blue :y :red :z :white}
           :position {:x 1 :y -1 :z 1}}
          (rotate-piece-around-axis :z true {:colors   {:x :red :y :blue :z :white}
                                             :position {:x 1 :y 1 :z 1}}))))

  (testing "rotate-piece-around-axis :x, counterclockwise, applies rotation and swap-faces"
    (is (=
          {:colors
                     {:x :red :y :white :z :blue}
           :position {:x 1 :y -1 :z 1}}
          (rotate-piece-around-axis :x false {:colors   {:x :red :y :blue :z :white}
                                              :position {:x 1 :y 1 :z 1}}))))

  (testing "rotate-piece-around-axis :x, clockwise, applies rotation and swap-faces"
    (is (=
          {:colors
                     {:x :red :y :white :z :blue}
           :position {:x 1 :y 1 :z -1}}
          (rotate-piece-around-axis :x true {:colors   {:x :red :y :blue :z :white}
                                             :position {:x 1 :y 1 :z 1}})))))

(deftest rotate-face-test
  (testing "Reversal :U and :U'"
    (is (= (rotate-face (rotate-face default-cube :U') :U) default-cube)))

  (testing "Reversal :D and :D'"
    (is (= (rotate-face (rotate-face default-cube :D') :D) default-cube)))

  (testing "Reversal :F and :F'"
    (is (= (rotate-face (rotate-face default-cube :F') :F) default-cube)))

  (testing "Reversal :B and :B'"
    (is (= (rotate-face (rotate-face default-cube :B') :B) default-cube)))

  (testing "Reversal :L and :L'"
    (is (= (rotate-face (rotate-face default-cube :L') :L) default-cube)))

  (testing "Reversal :R and :R'"
    (is (= (rotate-face (rotate-face default-cube :R') :R) default-cube)))

  (testing "Testing associative principal on :U and :D"
    (is (= (rotate-face (rotate-face default-cube :U) :D) (rotate-face (rotate-face default-cube :D) :U))))

  (testing "Testing associative principal on :L and :R"
    (is (= (rotate-face (rotate-face default-cube :L) :R) (rotate-face (rotate-face default-cube :R) :L))))

  (testing "Testing associative principal on :F and :B"
    (is (= (rotate-face (rotate-face default-cube :F) :B) (rotate-face (rotate-face default-cube :B) :F)))))

(deftest execute-algo-test
  (testing "Testing an algorithm on :R and :U"
    (is (= default-cube (algo default-cube '(:R :U :R' :U'
                                                      :R :U :R' :U'
                                                      :R :U :R' :U'
                                                      :R :U :R' :U'
                                                      :R :U :R' :U'
                                                      :R :U :R' :U')))))

  (testing "Testing an algorithm on :L and :U"
    (is (= default-cube (algo default-cube '(:L :U :L' :U'
                                                      :L :U :L' :U'
                                                      :L :U :L' :U'
                                                      :L :U :L' :U'
                                                      :L :U :L' :U'
                                                      :L :U :L' :U')))))

  (testing "Testing an algorithm on :F and :U"
    (is (= default-cube (algo default-cube '(:F :U :F' :U'
                                                      :F :U :F' :U'
                                                      :F :U :F' :U'
                                                      :F :U :F' :U'
                                                      :F :U :F' :U'
                                                      :F :U :F' :U')))))

  (testing "Testing an algorithm on :B and :U"
    (is (= default-cube (algo default-cube '(:B :U :B' :U'
                                                      :B :U :B' :U'
                                                      :B :U :B' :U'
                                                      :B :U :B' :U'
                                                      :B :U :B' :U'
                                                      :B :U :B' :U')))))

  (testing "Testing an algorithm on :R and :D"
    (is (= default-cube (algo default-cube '(:R :D :R' :D'
                                                      :R :D :R' :D'
                                                      :R :D :R' :D'
                                                      :R :D :R' :D'
                                                      :R :D :R' :D'
                                                      :R :D :R' :D')))))

  (testing "Testing an algorithm on :L and :D"
    (is (= default-cube (algo default-cube '(:L :D :L' :D'
                                                      :L :D :L' :D'
                                                      :L :D :L' :D'
                                                      :L :D :L' :D'
                                                      :L :D :L' :D'
                                                      :L :D :L' :D')))))

  (testing "Testing an algorithm on :F and :D"
    (is (= default-cube (algo default-cube '(:F :D :F' :D'
                                                      :F :D :F' :D'
                                                      :F :D :F' :D'
                                                      :F :D :F' :D'
                                                      :F :D :F' :D'
                                                      :F :D :F' :D')))))

  (testing "Testing an algorithm on :B and :D"
    (is (= default-cube (algo default-cube '(:B :D :B' :D'
                                                      :B :D :B' :D'
                                                      :B :D :B' :D'
                                                      :B :D :B' :D'
                                                      :B :D :B' :D'
                                                      :B :D :B' :D'))))))