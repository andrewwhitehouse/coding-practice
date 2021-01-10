(ns codewars.wave-test
  (:require [clojure.test :refer :all]
            [codewars.wave :refer :all]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest sample-test-cases
  (testing "with non-letter characters"
    (test-assert (wave "a       b    ") ["A       b    " "a       B    "]))
  (testing "with multiple words"
    (test-assert
      (wave "this is a few words")
      ["This is a few words" "tHis is a few words" "thIs is a few words" "thiS is a few words" "this Is a few words" "this iS a few words" "this is A few words" "this is a Few words" "this is a fEw words" "this is a feW words" "this is a few Words" "this is a few wOrds" "this is a few woRds" "this is a few worDs" "this is a few wordS"]))
  (testing "with an empty string"
    (test-assert (wave "") []))
  (testing "with whitespace around the string"
    (test-assert (wave " gap ") [" Gap " " gAp " " gaP "])))
