(ns isbn-verifier)

(defn- valid-format? [s]
  (and
    (= s (re-find #"[0-9]{9,10}X*" s))
    (= 10 (count s))))

(defn- mod11 [n] (mod n 11))

(defn isbn? [isbn]
  (let [char-value (fn [ch] (if (= \X ch) 10 (- (int ch) (int \0))))
        checksum-chars (->> isbn (remove #(= \- %)) (apply str))]
    (and
      (valid-format? checksum-chars)
      (->> checksum-chars
        reverse
        (map-indexed (fn [idx ch] (* (inc idx) (char-value ch))))
        (apply +)
        mod11
        zero?))))
