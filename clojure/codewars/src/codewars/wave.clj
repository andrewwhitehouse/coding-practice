(ns codewars.wave)

(defn wave [s]
  (loop [index 0
         ch-vec (mapv identity s)
         result []]
    (if (< index (count s))
      (let [ch (get s index)]
        (if (Character/isSpaceChar ch)
          (recur (inc index) ch-vec result)
          (recur (inc index)
                 ch-vec
                 (conj result (apply str (assoc ch-vec index (Character/toUpperCase ch)))))))
      result)))

;; This is from user [khoda](https://www.codewars.com/users/khoda)

(defn wave-khoda [string]
  (let [s (vec string)]
    (for [i (range (count s))
          :when (not= \space (s i))]
      (apply str (update s i #(Character/toUpperCase %))))))
