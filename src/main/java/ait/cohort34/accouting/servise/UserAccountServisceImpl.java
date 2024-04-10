package ait.cohort34.accouting.servise;

import ait.cohort34.accouting.dao.UserAccountRepository;
import ait.cohort34.accouting.dto.RolesDto;
import ait.cohort34.accouting.dto.UserDto;
import ait.cohort34.accouting.dto.UserEditDto;
import ait.cohort34.accouting.dto.UserRegisterDto;
import ait.cohort34.accouting.dto.exeption.AccountNotFoundExeption;
import ait.cohort34.accouting.model.UserAccount;
import ait.cohort34.forum_servise.post.dto.PostDto;
import ait.cohort34.forum_servise.post.dto.exeptions.PostNotFoundExeption;
import ait.cohort34.forum_servise.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServisceImpl implements UserAccountService{

    final UserAccountRepository userAccountRepository;
    final ModelMapper modelMapper;
    @Override
    public UserDto register(UserRegisterDto user) {
        if(user == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName());
        //userAccountRepository.save(user);
         return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount user = userAccountRepository.getUserAccountByLoginIgnoreCase(login).orElseThrow(AccountNotFoundExeption::new);
        return  modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto removeUser(String login) {
        return null;
    }

    @Override
    public UserDto updateUser(String login, UserEditDto userEditDto) {
        return null;
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        return null;
    }

    @Override
    public void changePassword(String login, String newPassword) {

    }
}
