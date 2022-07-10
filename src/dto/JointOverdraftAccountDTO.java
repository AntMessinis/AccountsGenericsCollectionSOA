package dto;

public class JointOverdraftAccountDTO extends AbstractDTO implements JoinableDTO{
    UserDTO secondHolder;

    public JointOverdraftAccountDTO() {
    }

    public JointOverdraftAccountDTO(UserDTO holder, String iban, double balance, UserDTO secondHolder) {
        super(holder, iban, balance);
        this.secondHolder = new UserDTO(secondHolder);;
    }

    public JointOverdraftAccountDTO(JointOverdraftAccountDTO dto) {
        super(dto);
        this.secondHolder = dto.getSecondHolder();
    }

    @Override
    public UserDTO getSecondHolder() {
        return secondHolder;
    }

    @Override
    public void setSecondHolder(UserDTO dto) {
        secondHolder = new UserDTO(dto);
    }
}
