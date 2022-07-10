package dto;

public class JointAccountDTO extends AbstractDTO implements JoinableDTO{
    UserDTO secondHolder;

    public JointAccountDTO() {
    }

    public JointAccountDTO(UserDTO holder, String iban, double balance, UserDTO secondHolder) {
        super(holder, iban, balance);
        this.secondHolder =  new UserDTO(secondHolder);;
    }

    public JointAccountDTO(JointAccountDTO dto) {
        super(dto);
        this.secondHolder = dto.getSecondHolder();
    }

    @Override
    public UserDTO getSecondHolder() {
        return secondHolder;
    }

    @Override
    public void setSecondHolder(UserDTO secondHolder) {
        this.secondHolder = new UserDTO(secondHolder);
    }
}
