package ait.cohort34.accouting.servise;

import ait.cohort34.accouting.dao.UserAccountRepository;
import ait.cohort34.accouting.dto.RolesDto;
import ait.cohort34.accouting.dto.UserDto;
import ait.cohort34.accouting.dto.UserEditDto;
import ait.cohort34.accouting.dto.UserRegisterDto;
import ait.cohort34.accouting.dto.exeption.AccountNotFoundExeption;
import ait.cohort34.accouting.dto.exeption.IncorrectroleExeption;
import ait.cohort34.accouting.model.UserAccount;
import ait.cohort34.forum_servise.post.dto.exeptions.PostNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
final UserAccountRepository userAccountRepository;
final ModelMapper modelMapper;
@Override
public UserDto register(UserRegisterDto userRegisterDto) {
    if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
        throw new AccountNotFoundExeption();
    }
    UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
    String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
    userAccount.setPassword(password);
    userAccountRepository.save(userAccount);
    return modelMapper.map(userAccount, UserDto.class);
}

@Override
public UserDto getUser(String login) {
    UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(AccountNotFoundExeption::new);
    return modelMapper.map(userAccount, UserDto.class);
}

@Override
public UserDto removeUser(String login) {
    UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(AccountNotFoundExeption::new);
    userAccountRepository.delete(userAccount);
    return modelMapper.map(userAccount, UserDto.class);
}

@Override
public UserDto updateUser(String login, UserEditDto userEditDto) {
    UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(AccountNotFoundExeption::new);
    if (userEditDto.getFirstName() != null) {
        userAccount.setFirstName(userEditDto.getFirstName());
    }
    if (userEditDto.getLastName() != null) {
        userAccount.setLastName(userEditDto.getLastName());
    }
    userAccountRepository.save(userAccount);
    return modelMapper.map(userAccount, UserDto.class);
}

@Override
public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
    UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(AccountNotFoundExeption::new);
    boolean res;
    try {
        if (isAddRole) {
            res = userAccount.addRole(role);
        } else {
            res = userAccount.removeRole(role);
        }
    }catch (Exception e){
        throw new IncorrectroleExeption();
    }
    if(res) {
        userAccountRepository.save(userAccount);
    }
    return modelMapper.map(userAccount, RolesDto.class);
}

@Override
public void changePassword(String login, String newPassword) {
    UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(AccountNotFoundExeption::new);
    String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    userAccount.setPassword(password);
    userAccountRepository.save(userAccount);
}
}