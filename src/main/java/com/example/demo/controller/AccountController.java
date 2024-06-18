package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	CustomerRepository customerRepository;

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		// 名前が空の場合にエラーとする
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力してください");
			return "login";
		}

		List<Customer> customerList = customerRepository.findByEmailAndPassword(email, password);
		if (customerList == null || customerList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		Customer customer = customerList.get(0);

		// セッション管理されたアカウント情報にIDと名前をセット
		account.setId(customer.getId());
		account.setName(customer.getName());

		// 「/items」へのリダイレクト
		return "redirect:/items";
	}

	// 会員登録画面の表示
	@GetMapping("/account")
	public String create() {
		return "accountForm";
	}

	// 会員登録実行
	@PostMapping("/account")
	public String store(
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0 || address.length() == 0 || tel.length() == 0 || email.length() == 0
				|| password.length() == 0) {
			errorList.add("・すべての項目を入力してください");
		}
		if (checkLogic("^[0-9]+$", tel)) {
			errorList.add("・電話番号は半角数字のみ入力してください");
		}
		if (password.length() < 8 || password.length() > 12) {
			errorList.add("・パスワードは8文字以上12文字以下にしてください");
		}

		// メールアドレス存在チェック
		List<Customer> customerList = customerRepository.findByEmail(email);
		if (customerList != null && customerList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("・このメールアドレスは既に登録済です");
		}
		customerList = customerRepository.findByTel(tel);
		if (customerList != null && customerList.size() > 0) {
			// 登録済みの電話番号が存在した場合
			errorList.add("・この電話番号は既に登録済です");
		}

		// エラー発生時は新規登録画面に戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("address", address);
			model.addAttribute("tel", tel);
			model.addAttribute("email", email);
			return "accountForm";
		}

//		Customer customer = new Customer(name, address, tel, email, password);
//		customerRepository.save(customer);
		model.addAttribute("name", name);
		model.addAttribute("address", address);
		model.addAttribute("tel", tel);
		model.addAttribute("email", email);
		model.addAttribute("password",password);
		return "accountConfirm";
	}
	
	@PostMapping("/addaccount")
	public String addaccount(
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		Customer customer = new Customer(name, address, tel, email, password);
		customerRepository.save(customer);
		return "redirect:/login";
	}

	public static boolean checkLogic(String regex, String target) {
		boolean result = false;
		if (target == null || target.isEmpty())
			return false;
		// 引数に指定した正規表現regexがtargetにマッチするか確認する
		Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
		Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
		if (m1.matches()) {// 照合結果をtrueかfalseで取得
			result = false;
		} else {
			result = true;
		}
		return result;
	}
}
