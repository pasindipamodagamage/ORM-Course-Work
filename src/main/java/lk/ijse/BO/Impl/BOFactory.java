package lk.ijse.BO.Impl;

import lk.ijse.BO.SuperBO;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BoType{
        User, Student, Payment, Course, Student_Course

    }
    public SuperBO getBo(BoType boType){
        switch (boType){

            case User:
                return new UserBOImpl();
            case Student:
                return new StudentBOImpl();
            case Payment:
                return  new PaymentBOImpl();
            case Course:
                return  new CourseBOImpl();
                case Student_Course:
                return  new Student_CourseBOImpl();
            default:
                return null;

        }
    }
}
