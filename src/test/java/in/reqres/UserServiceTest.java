package in.reqres;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import models.NewUserData;
import models.PartialUpdateUserData;
import models.SingleUserData;
import models.UpdatedUserData;
import reqres.in.UserService;

public class UserServiceTest extends BaseTest {

	UserService userService = new UserService();

	@Test
	public void getAllUsersTest() {
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("page", "2");
		Map allUsersData = userService.getAllUsersDetails(queryParams, 200);
		Assert.assertTrue(allUsersData.get("page").toString().contains(queryParams.get("page")));
	}

	@Test
	public void getSingleUserTest() {
		Map<String, String> pathParam = new LinkedHashMap<>();
		pathParam.put("id", "2");
		SingleUserData singleUserData = userService.getSingleUserDetails(pathParam, 200);
		Assert.assertTrue(singleUserData.getId() == Integer.parseInt(pathParam.get("id")));
	}

	@Test
	public void addNewUserTest() {
		String requestBoy = "{\r\n" + "    \"name\": \"Vennkat\",\r\n" + "    \"job\": \"Automation Test Engineer\"\r\n"
				+ "}";
		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("Accept", "*/*");
		headers.put("x-api-key", "reqres-free-v1");

		NewUserData newUserData = userService.addUserDetails(headers, requestBoy, 201);
		Assert.assertTrue(newUserData.getName().equals("Vennkat"));
		Assert.assertTrue(newUserData.getJob().equals("Automation Test Engineer"));
		Assert.assertNotNull(newUserData.getId());
	}

	@Test
	public void updateUserTest() {
		String requestBoy = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"Manual Test Engineer\"\r\n"
				+ "}";
		Map<String, String> pathParam = new LinkedHashMap<>();
		pathParam.put("id", "2");

		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("Accept", "*/*");
		headers.put("x-api-key", "reqres-free-v1");

		UpdatedUserData updatedUserData = userService.updateUserDetails(pathParam, headers, requestBoy, 200);

		Assert.assertTrue(updatedUserData.getName().equals("morpheus"));
		Assert.assertTrue(updatedUserData.getJob().equals("Manual Test Engineer"));
	}

	@Test
	public void updatePartialUserDataTest() {
		String requestBoy = "{\r\n" + "    \"job\": \"Quality Assurance Engineer\"\r\n" + "}";

		Map<String, String> pathParam = new LinkedHashMap<>();
		pathParam.put("id", "2");

		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("Accept", "*/*");
		headers.put("x-api-key", "reqres-free-v1");

		PartialUpdateUserData partialUpdateData = userService.updatePartialUserData(pathParam, headers, requestBoy,
				200);

		Assert.assertTrue(partialUpdateData.getJob().equals("Quality Assurance Engineer"));
	}

	@Test
	public void deleteUserTest() {
		Map<String, String> pathParam = new LinkedHashMap<>();
		pathParam.put("id", "2");
		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("x-api-key", "reqres-free-v1");
		String responseStr = userService.deleteUser(pathParam, headers, 204);

		Assert.assertNull(responseStr);
	}

}
