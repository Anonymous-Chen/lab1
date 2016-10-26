package lab1;

import java.util.Scanner; //����
import java.util.regex.Matcher; //ƥ��
import java.util.regex.Pattern; //ģʽ

/**
 * Original annotation��Ϊʵ����ʽ�ļ�,
 * @author gaoshuhang
 *
 */
public class Lab1//I refactor class name from lab1_1 to Lab1. It may cause you can't use git diff. But I think we should have this changed.
{
	//Define scanner as static to avoid resource leak and initialize Scanner too many times.It will be closed on exit command.
	private static Scanner sc = new Scanner(System.in);
	
	// ����������ʽ
	private static String pattern1 = "(([0-9]+|[a-z])[\\+|\\*])+([0-9]+|[a-z])";
	private static String pattern2 = "!simplify( [a-z]=[0-9]+)*";
	private static String pattern3 = "[a-z]=[0-9]+";
	private static String pattern4_1 = "[a-z]";
	private static String pattern4_2 = "[0-9]+";
	private static String pattern5 = "!d/d[a-z]";
	private static String pattern6 = "[a-z]$";

	/**
	 * entrance function
	 * 
	 * @param args launch program command args
	 */
	public static void main(String[] args)
	{
		System.out.println("��������ʽ:");
		String line1_1[] = input();

		while (Integer.parseInt(line1_1[0]) != 0)
		{
			if (Integer.parseInt(line1_1[0]) == -1)
			{
				System.out.println("��������ȷ�ı��ʽ;");
			}
			else if (Integer.parseInt(line1_1[0]) == -2)
			{
				break;
			}
			else
			{
				System.out.println("��������ʽ����������;");
			}

			System.out.println("�Ƿ��ٴ����������Y/N��");
			String sinput = "0";

			while (sinput == "0")
			{
				sinput = sc.nextLine();

				switch (sinput)
				{
				case "Y":
				case "y":
					line1_1[0] = "0";
					System.out.println("���ٴ�������ʽ:");
					line1_1 = input();
					break;
				case "N":
				case "n":
					line1_1[0] = "-2";
					System.out.println("End of program,thanks for using!");
					break;
				default:
					sinput = "0";
					System.out.println("Error Input!");
					System.out.println("Please input again!");
				}
			}
		}

		// ����Ϸ������
		while (Integer.parseInt(line1_1[0]) == 0)
		{

			System.out.println("����������:");
			String line1_2[] = input();

			// �Բ�ͬ�����������
			switch (line1_2[0])
			{

			case "1": // ! simplify
				String strs1[][] = new String[27][2];
				strs1 = expression1(strs1, line1_2[1]);
				simplify(strs1, line1_1[1]);
				break;
			case "2":// !d/d var
				System.out.println("1");
				derivative(line1_1[1], line1_2[1]);
				break;
			default:// �쳣�ж�
				System.out.println("Error Input!");
			}

			System.out.println("�Ƿ��ٴ����������Y/N��");
			String sinput = "0";

			while (sinput == "0")
			{
				sinput = sc.nextLine();

				switch (sinput)
				{
				case "Y":
				case "y":
					line1_1[0] = "0";
					break;
				case "N":
				case "n":
					line1_1[0] = "-1";
					System.out.println("End of program,thanks for using!");
					sc.close();
					break;
				default:
					sinput = "0";
					System.out.println("Error Input!");
					System.out.println("Please input again!");
				}
			}
		}
	}

	/**
	 * handle input. I(in Lab4) changed this function for better performance.
	 * 
	 * @return splited string
	 */
	public static String[] input()
	{

		// ����String��
		boolean checked = false; // I add this variable to avoid string casted to Integer. string.equals() also works with less memory but it's slower.
		String line[] = new String[2];
		line[0] = "-1";
		String sinput = sc.nextLine();

		// ƥ��
		Matcher m = Pattern.compile(pattern1).matcher(sinput);
		Matcher m1 = Pattern.compile(pattern2).matcher(sinput);
		Matcher m9_1 = Pattern.compile(pattern5).matcher(sinput);
		Matcher m9_2 = Pattern.compile(pattern6).matcher(sinput);

		// ����ƥ��
		if (m.matches())
		{
			checked = true;
			line[0] = "0";
			line[1] = m.group(0);
			System.out.println("����Ϊ:" + line[1]);
		}

		else if (m1.matches())
		{
			checked = true;
			line[0] = "1";
			line[1] = m1.group(0);
			System.out.println("����Ϊ:" + line[1]);

		}

		else if (m9_1.matches())
		{
			checked = true;
			line[0] = "2";
			if (m9_2.find())
			{
				line[1] = m9_2.group(0);
			}
			System.out.println("����Ϊ:" + m9_1.group(0));

		}

		// ���������ж�
		if (!checked)
		{
			System.out.println("NO MATCH");
		}

		return line;
	}

	// ���� simplify���������
	public static String[][] expression1(String strs1[][], String line)
	{

		Matcher m2 = Pattern.compile(pattern3).matcher(line);
		int count = 0;

		// ���֡�var =num �������ַ�������ʽ���
		while (m2.find())
		{
			Matcher m3 = Pattern.compile(pattern4_1).matcher(m2.group(0));
			Matcher m4 = Pattern.compile(pattern4_2).matcher(m2.group(0));
			if (m3.find())
			{
				strs1[count][0] = m3.group(0);
			}
			if (m4.find())
			{
				strs1[count][1] = m4.group(0);
			}

			count++;
		}

		// ���桰count����ֵ����¼��������
		strs1[26][0] = String.valueOf(count);

		return strs1;
	}

	/**
	 * simplify function
	 * 
	 * @param strs1
	 * @param line
	 */
	public static void simplify(String strs1[][], String line)
	{
		int sum = 0;
		String strs2[][] = new String[27][2];

		int count = Integer.parseInt(strs1[26][0]);

		// ��ʾû��δ֪�������ԭʽ
		if (count != 0)
		{
			// �����滻δ֪��
			for (int i = 0; i < count; i++)
			{
				Matcher m5 = Pattern.compile(strs1[i][0]).matcher(line);
				line = m5.replaceAll(strs1[i][1]); // �滻����ַ���
			}

			// ����+���ֽ��滻����ַ���
			Pattern pattern2 = Pattern.compile("[+]+");
			String[] strs = pattern2.split(line);

			count = 0;

			// ����ֽ����ַ���
			for (int i = 0; i < strs.length; i++)
			{

				int num = 1;
				String str2 = null;

				// ƥ�����ֺ���ĸ
				Matcher m6 = Pattern.compile(pattern4_1).matcher(strs[i]);
				Matcher m7 = Pattern.compile(pattern4_2).matcher(strs[i]);

				// ƥ������ֲ��۳�
				while (m7.find())
				{
					num *= Integer.parseInt(m7.group(0));
				}

				// �ۼ�����
				if (!m6.find())
				{
					sum += num;
				}
				else
				{ // �������ĸ����
					Matcher m8 = Pattern.compile(pattern4_1).matcher(strs[i]);
					m8.find();
					str2 = m8.group(0);

					// ��ȡһ���е���ĸ�ɷ֣���ϵ���ֿ�����
					while (m8.find())
					{
						str2 = str2 + "*" + m8.group(0);
					}
					for (int j = 0; j <= 27; j++)
					{
						if (strs2[j][0] == null)
						{
							strs2[j][0] = str2;
							strs2[j][1] = String.valueOf(num);
							count++;
							break;
						}
						else
						{
							if (str2.compareTo(strs2[j][0]) == 0)
							{

								if (strs2[j][1] != null)
								{
									int num1 = Integer.parseInt(strs2[j][1])
											+ num;
									strs2[j][1] = String.valueOf(num1);

								}
								else
								{
									strs2[j][1] = String.valueOf(num);
								}
							}
						}
					}
				}
			}

			// ���������
			System.out.print("������Ϊ��" + sum);
			for (int i = 0; i < count; i++)
			{
				if (Integer.parseInt(strs2[i][1]) == 1)
				{
					System.out.print("+" + strs2[i][0]);
				}
				else
				{
					System.out.print("+" + strs2[i][1] + "*" + strs2[i][0]);
				}
			}
			System.out.println();

		}
		else
		{
			System.out.println(line);
		}

		return;
	}

	/**
	 * derivative function
	 * 
	 * @param line
	 * @param word
	 */
	public static void derivative(String line, String word)
	{

		String strs3[][] = new String[27][2];
		int count = 0;

		// �Ա��ʽ����+�����зָ�
		Pattern pattern2 = Pattern.compile("[+]+");
		String[] strs = pattern2.split(line);

		for (int i = 0; i < strs.length; i++)
		{

			Matcher m10 = Pattern.compile(word).matcher(strs[i]);

			// ����һ���д���δ֪���Ĵ���
			if (m10.find())
			{
				int count1 = 1;

				while (m10.find())
				{
					count1++;
				}

				// �ô����滻һ������δ֪��
				Matcher m11 = Pattern.compile(word).matcher(strs[i]);
				strs[i] = m11.replaceFirst(String.valueOf(count1)); // �滻����ַ���

				int num = 1;
				Matcher m12 = Pattern.compile(pattern4_2).matcher(strs[i]);

				// ����ϵ��
				while (m12.find())
				{
					num *= Integer.parseInt(m12.group(0));
				}
				strs3[count][0] = String.valueOf(num);

				Matcher m13 = Pattern.compile(pattern4_1).matcher(strs[i]);
				String str2 = null;

				// ����ʣ��δ֪��
				if (m13.find())
				{
					str2 = m13.group(0);
				}

				while (m13.find())
				{
					str2 = str2 + "*" + m13.group(0);
				}
				strs3[count][1] = str2;

				count++;
			}
		}

		// ����󵼽��
		if (count == 0)
		{
			System.out.println("0");
		}
		else
		{
			if (strs3[0][1] == null)
			{
				System.out.print("�󵼽��Ϊ��" + strs3[0][0]);
			}
			else
			{
				System.out.print("�󵼽��Ϊ��" + strs3[0][0] + "*" + strs3[0][1]);
			}

			for (int i = 1; i < count; i++)
			{
				if (strs3[i][1] == null)
				{
					System.out.print("+" + strs3[i][0]);
				}
				else
				{
					System.out.print("+" + strs3[i][0] + "*" + strs3[i][1]);
				}

			}
			System.out.println();
		}

	}

}

