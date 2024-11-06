package lk.ijse.BO;

import lk.ijse.DTO.CourseDTO;
import lk.ijse.DTO.PaymentDTO;

public interface PaymentBO extends SuperBO {
    public boolean save(PaymentDTO dto) throws Exception;

    public boolean update(PaymentDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;
}
