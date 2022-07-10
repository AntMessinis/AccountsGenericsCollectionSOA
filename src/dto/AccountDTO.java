package dto;

public class AccountDTO extends AbstractDTO{

    public AccountDTO(){
        super();
    }

    public AccountDTO(AbstractDTO dto) {
        super(dto);
    }

    public AccountDTO(UserDTO holder, String iban, double balance) {
        super(holder, iban, balance);
    }
}
