public class OffByN implements CharacterComparator{
    private int N;

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs((int) x - (int) y) == N){
            return true;
        }
        return false;
    }
    public OffByN(int n) {
        N = n;
    }
}
