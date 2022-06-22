import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
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
    private int indexMapperX(int x) {
        if (x < 0) {
            return width + x;
        } else if (x >= width) {
            return x - width;
        } else {
            return x;
        }
    }
    private int indexMapperY(int y) {
        if (y < 0) {
            return height + y;
        } else if (y >= height) {
            return y - height;
        } else {
            return y;
        }
    }
    public double energy(int x, int y) {
        Color colorAtXMinus1 = picture.get(indexMapperX(x - 1), y);
        Color colorAtXPlus1 = picture.get(indexMapperX(x + 1), y);
        int deltaRedX = colorAtXMinus1.getRed() - colorAtXPlus1.getRed();
        int deltaGreenX = colorAtXMinus1.getGreen() - colorAtXPlus1.getGreen();
        int deltaBlueX = colorAtXMinus1.getBlue() - colorAtXPlus1.getBlue();
        Color colorAtYMinus1 = picture.get(x, indexMapperY(y - 1));
        Color colorAtYPlus1 = picture.get(x, indexMapperY(y + 1));
        int deltaRedY = colorAtYPlus1.getRed() - colorAtYMinus1.getRed();
        int deltaGreenY = colorAtYMinus1.getGreen() - colorAtYPlus1.getGreen();
        int deltaBlueY = colorAtYMinus1.getBlue() - colorAtYPlus1.getBlue();
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
