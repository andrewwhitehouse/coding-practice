(ns andreww.hard.p92)

(def __
  (fn [s]
    (let [subtractives {"IV" "IIII", "IX" "VIIII", "XL" "XXXX", "XC" "LXXXX", "CD" "CCCC", "CM" "DCCCC"}
          char-value-map {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}]
      (->>
        (reduce
          (fn [s [from to]] (clojure.string/replace s (re-pattern from) to))
          s
          subtractives)
        (map char-value-map)
        (apply +)))))

(comment

  (= 14 (__ "XIV"))

  (= 827 (__ "DCCCXXVII"))

  (= 3999 (__ "MMMCMXCIX"))

  (= 48 (__ "XLVIII"))

  )

; austintaylor
(fn [s]
  (let [roman {"M" 1000 "CM" 900 "D" 500 "CD" 400
    "C" 100 "XC" 90 "L" 50 "XL" 40 "X" 10 "IX" 9
    "V" 5 "IV" 4 "I" 1}]
    (reduce + (map roman
      (re-seq #"CM|CD|XC|XL|IX|IV|[MDCLXVI]" s)))))

; nikelandjelo
#(->> (map {\C 100 \D 500 \I 1 \L 50 \M 1000 \V 5 \X 10} %)
      (partition 2 1 [0])
      (map (fn [[a b]] (if (< a b) (- a) a)))
      (apply +))
