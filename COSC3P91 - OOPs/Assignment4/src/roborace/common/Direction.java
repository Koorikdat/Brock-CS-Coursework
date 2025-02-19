package roborace.common;

public enum Direction {
    North {
        @Override
        public Direction rotate90(boolean clockwise) {
            Direction result;
            if (clockwise) {
                result = East;
            } else {
                result = West;
            }
            return result;
        }
    },
    East {
        @Override
        public Direction rotate90(boolean clockwise) {
            Direction result;
            if (clockwise) {
                result = South;
            } else {
                result = North;
            }
            return result;
        }
    },
    South {
        @Override
        public Direction rotate90(boolean clockwise) {
            Direction result;
            if (clockwise) {
                result = West;
            } else {
                result = East;
            }
            return result;
        }
    },
    West {
        @Override
        public Direction rotate90(boolean clockwise) {
            Direction result;
            if (clockwise) {
                result = North;
            } else {
                result = South;
            }
            return result;
        }
    };

    public abstract Direction rotate90(boolean clockwise);

    public Direction rotate180() {
        return rotate90(true).rotate90(true);
    }
}