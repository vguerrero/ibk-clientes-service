package add.commons.ibk.sample.service;

public enum ClienteStatusEnum {
    CORRECT_STATUS {
        public String toString() {
            return "0000";
        }
    },

    NOT_EXIST_STATUS {
        public String toString() {
            return "9999";
        }
    }
}
