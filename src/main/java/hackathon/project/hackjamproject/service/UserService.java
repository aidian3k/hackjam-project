package hackathon.project.hackjamproject.service;

import hackathon.project.hackjamproject.dto.auction.UserAuctionDTO;
import hackathon.project.hackjamproject.dto.user.UserCreationDTO;
import hackathon.project.hackjamproject.dto.user.UserDTO;
import hackathon.project.hackjamproject.entity.User;
import hackathon.project.hackjamproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User createUser(UserCreationDTO userCreationDTO) {
		User user = User
			.builder()
			.name(userCreationDTO.getName())
			.surname(userCreationDTO.getSurname())
			.email(userCreationDTO.getEmail())
			.password(userCreationDTO.getPassword())
			.localization(userCreationDTO.getLocalization())
			.build();

		return userRepository.save(user);
	}

	public User updateCurrentUser(User updatedUser) {
		return userRepository.save(updatedUser);
	}

	public List<UserDTO> getAllUsers() {
		return userRepository
			.findAll()
			.stream()
			.map(user ->
				UserDTO
					.builder()
					.name(user.getName())
					.email(user.getEmail())
					.surname(user.getSurname())
					.id(user.getId())
					.build()
			)
			.toList();
	}

	public UserAuctionDTO getUserInfoForAuction(Long userId, Long bidPrice) {
		User userInfo = getUserById(userId);

		return UserAuctionDTO
			.builder()
			.name(userInfo.getName())
			.surname(userInfo.getSurname())
			.bidPrice(bidPrice)
			.build();
	}

	public User getUserById(Long userId) {
		return userRepository
			.findById(userId)
			.orElseThrow(() ->
				new IllegalStateException("User with given id could not be found!")
			);
	}
}
