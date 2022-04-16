import java.lang.Math;
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Planet p)
    {
        double r = this.calcDistance(p);
        double G = 6.67e-11;
        return G * this.mass * p.mass / (r * r);
    }
    public double calcForceExertedByX(Planet p)
    {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return F * dx / r;
    }

    public double calcForceExertedByY(Planet p)
    {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] ps)
    {
        double result = 0;
        for (Planet p : ps)
        {
            if (this.equals(p))
            {
                continue;
            }
            result += this.calcForceExertedByX(p);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] ps)
    {
        double result = 0;
        for (Planet p : ps)
        {
            if (this.equals(p))
            {
                continue;
            }
            result += this.calcForceExertedByY(p);
        }
        return result;
    }

    public void update(double dt, double fX, double fY)
    {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
    public void draw()
    {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+imgFileName);
        return;
    }
}
