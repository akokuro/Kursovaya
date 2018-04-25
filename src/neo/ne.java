package neo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class ne {
	public static URL page = null;
	public static String result = "";
	public static String patt = "<li>";
	public static int last_ind = 0;
	public static String str = "ничего нет";
	public static String word = "";
	public static String l;
	public static String query;

/*	
	public static void main(String s) throws IOException {
		ReadWord();
		Pars(l);
		Safe();
	}
*/
	
	public static void ReadWord() {
		Scanner b = new Scanner(System.in);
		l = b.nextLine();
		try {
			query = URLEncoder.encode(l, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(
					"Упа~ло при попытке закодировать строку в кусок УРЛ, эх - беда, печаль, можете попробовать снова");
			e.printStackTrace();
		}
		b.close();
	}

	public static String Pars(String query) {
		try {
			System.out.println(query);
			query = URLEncoder.encode(query, "UTF-8");
		} catch (Exception e) {
			System.out.println(
					"Упа~ло при попытке закодировать строку в кусок УРЛ, эх - беда, печаль, можете попробовать снова");
			e.printStackTrace();
		}
		try {
			Path file = Paths.get("C:\\Games\\Magic.txt");
			page = new URL("http://rifma-online.ru/rifma/" + query + "/");
			result = "";
			try {
				InputStream in = page.openStream();
				Files.copy(in, file, StandardCopyOption.REPLACE_EXISTING);
			} catch (RuntimeException e) {
				System.out.println("С этим вашим интернетом беда полная");
			}
			
			Scanner a = new Scanner(page.openStream());
			boolean flag = 1 == 2;
			System.out.println(a);
			while (a.hasNext()) {
				str = a.nextLine();
				for (int i = 0; i < str.length(); i++) {
					last_ind = str.indexOf(patt, last_ind);
					if (last_ind == -1) {
						break;
					} else {
						last_ind += 20;
						while (str.charAt(last_ind) != '>') {
							last_ind++;
						}
						last_ind++;
						while (str.charAt(last_ind) != '<') {
							word += str.charAt(last_ind);
							last_ind++;
						}
						word = new String(word.getBytes(), "UTF-8");
						if (!flag) {
							System.out.println(word);
							flag = !flag;
						}
						result += word;
						result = result + "\r\n";
						word = "";
					}
				}
			}
			//Safe();
			return result;
		} catch (Exception e) {
			return ("Упало!!!! Парс сломался!!!! Караул!!!! Кто спасет нас от беды?!!!");
		}

	}

	public static void Safe() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Games\\word.txt"))) {
			bw.write(result);
		} catch (IOException ex) {
			System.out.println(
					"Упало при попытке сохранить в файл - еще большая печаль, эх. Труды все напрасны. Как тщетен мир.");
			System.out.println(ex.getMessage());
		}
	}
}
