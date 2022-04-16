public class NBody {
    public static double readRadius(String fileName)
    {
        In in = new In(fileName);
        int N = in.readInt();
        double result = in.readDouble();
        return result;
    }

    public static Planet[] readPlanets(String fileName)
    {
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();
        Planet[] result = new Planet[N];
        for (int i = 0; i < N; i++)
        {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            result[i] = p;
        }
        return result;
    }

    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int N = planets.length;
        StdDraw.clear();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
        for (Planet p : planets)
        {
            p.draw();
        }
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T)
        {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++)
            {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++)
            {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
            for (Planet p : planets)
            {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.clear();
            time += dt;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
        return;
    }
}
