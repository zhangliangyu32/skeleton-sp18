import edu.princeton.cs.algs4.Picture;
public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
    }
    public Picture picture() {
        return picture;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public double energy(int x, int y) {
        int colorAtXMinus1 = picture.getRGB(Math.max(x - 1, 0), y);
        int colorAtXPlus1 = picture.getRGB(Math.min(width - 1, x + 1), y);
        int deltaRedX = (colorAtXMinus1 & 0xff0000) >> 16 - (colorAtXPlus1 & 0xff0000) >> 16;
        int deltaGreenX = (colorAtXMinus1 & 0xff00) >> 8 - (colorAtXPlus1 & 0xff00) >> 8;;
        int deltaBlueX = (colorAtXMinus1 & 0xff) - (colorAtXPlus1 & 0xff);
        int colorAtYMinus1 = picture.getRGB(x, Math.max(y - 1, 0));
        int colorAtYPlus1 = picture.getRGB(x, Math.min(height - 1, y + 1));
        int deltaRedY = (colorAtYMinus1 & 0xff0000) >> 16 - (colorAtYPlus1 & 0xff0000) >> 16;
        int deltaGreenY = (colorAtYMinus1 & 0xff00) >> 8 - (colorAtYPlus1 & 0xff00) >> 8;;
        int deltaBlueY = (colorAtYMinus1 & 0xff) - (colorAtYPlus1 & 0xff);
        return deltaBlueX * deltaBlueX + deltaGreenX * deltaGreenX + deltaRedX * deltaRedX +
                deltaBlueY * deltaBlueY + deltaGreenY * deltaGreenY + deltaRedY * deltaRedY;
    }
    public int[] findHorizontalSeam() {
        double M[][] = new double[width][height];
        for(int i = 0; i < width; i++) {
            if (i == 0) {
                for (int j = 0; j < height; j++) {
                    M[i][j] = energy(i, j);
                }
                continue;
            }
            for (int j = 0; j < height; j++) {
                M[i][j] = energy(i, j) + Math.min(M[i - 1][j], Math.min(M[i - 1][Math.max(j - 1, 0)],
                        M[i - 1][Math.min(j + 1, height - 1)]));
            }
        }
        int minIndex = 0;
        for(int j = 0; j < height; j++) {
            if (M[width - 1][j] < M[width - 1][j]) {
                minIndex = j;
            }
        }
        int[] result = new int[width];
        result[width - 1] = minIndex;
        for (int i = width - 2; i >= 0; i--) {
            result[i] = M[i][minIndex] <= M[i][Math.max(minIndex - 1, 0)] ? minIndex : Math.max(minIndex - 1, 0);
            result[i] = M[i][result[i]] <= M[i][Math.min(minIndex + 1, height - 1)] ? result[i] : Math.min(minIndex + 1, height - 1);
            minIndex = result[i];
        }
        return result;
    }
    public int[] findVerticalSeam() {
        double M[][] = new double[width][height];
        for(int j = 0; j < height; j++) {
            if (j == 0) {
                for (int i = 0; i < width; i++) {
                    M[i][j] = energy(i, j);
                }
                continue;
            }
            for (int i = 0; i < width; i++) {
                M[i][j] = energy(i, j) + Math.min(M[i][j - 1], Math.min(M[Math.max(i - 1, 0)][j - 1],
                        M[Math.min(i + 1, width - 1)][j - 1]));
            }
        }
        int minIndex = 0;
        for(int i = 0; i < width; i++) {
            if (M[i][height - 1] < M[minIndex][height - 1]) {
                minIndex = i;
            }
        }
        int[] result = new int[height];
        result[height - 1] = minIndex;
        for (int j = height - 2; j >= 0; j--) {
            result[j] = M[minIndex][j] <= M[Math.max(minIndex - 1, 0)][j] ? minIndex : Math.max(minIndex - 1, 0);
            result[j] = M[result[j]][j] <= M[Math.min(minIndex + 1, width - 1)][j] ? result[j] : Math.min(minIndex + 1, width - 1);
            minIndex = result[j];
        }
        return result;
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover sr = new SeamRemover();
        this.picture = sr.removeHorizontalSeam(picture, seam);
        height --;
        return;
    }
    public void removeVerticalSeam(int[] seam) {
        SeamRemover sr = new SeamRemover();
        this.picture = sr.removeVerticalSeam(picture, seam);
        width --;
        return;
    }
}
