(ns run-length-encoding)

(defn- compress [[ch & _ :as char-seq]]
  (let [seq-length (count char-seq)]
    (if (= 1 seq-length)
      (str ch)
      (str seq-length ch))))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (map compress)
       (apply str)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (->> (re-seq #"(\d*)([a-zA-Z ])" cipher-text)
       (mapcat (fn [[_ cnt ch]] (if (empty? cnt) ch (repeat (Integer. cnt) ch))))
       (apply str)))
