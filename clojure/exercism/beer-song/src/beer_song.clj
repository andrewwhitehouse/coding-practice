(ns beer-song)

(defn verse
  "Returns the nth verse of the song."
  [num]
  (let [bottles (fn [num] (condp = num
                            0 "no more bottles"
                            1 "1 bottle"
                            (str num " bottles")))]
    (if (zero? num)
      (str
        "No more bottles of beer on the wall, no more bottles of beer.\n"
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
      (str
        (bottles num) " of beer on the wall, " (bottles num) " of beer.\n"
        "Take " (if (= 1 num) "it" "one") " down and pass it around, " (bottles (dec num)) " of beer on the wall.\n"))))


(defn sing
  " Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung. "
  ([start]
   (sing start 0))
  ([start end]
   (clojure.string/join "\n" (map verse (range start (dec end) -1)))))
