(ns codewars.longest-test
  (:require [clojure.test :refer :all]
            [codewars.longest :refer :all]))


(defn test-assert [act exp]
  (is (= act exp)))

(deftest a-test1
  (testing "longest"
    (test-assert(longest "aretheyhere", "yestheyarehere"), "aehrsty")
    (test-assert(longest "loopingisfunbutdangerous", "lessdangerousthancoding"), "abcdefghilnoprstu")
    (test-assert(longest "inmanylanguages", "theresapairoffunctions"), "acefghilmnoprstuy")
    ))
