package dto;

public class OverdraftAccountDTO extends AbstractDTO{
    public OverdraftAccountDTO() {
    }

    public OverdraftAccountDTO(AbstractDTO dto) {
        super(dto);
    }

    public OverdraftAccountDTO(UserDTO holder, String iban, double balance) {
        super(holder, iban, balance);
    }
}
