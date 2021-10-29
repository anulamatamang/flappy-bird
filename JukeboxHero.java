import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 * 
 * @author Laila-Nisreen Saadi
 *
 */
public class JukeboxHero
{

	public static void main(String [] args)
	{
		Scanner kbd = new Scanner(System.in); 
		String menuTitle;

		ArrayList<Song> songList = new ArrayList<Song>();
		ArrayList<Song> sResults = new ArrayList<Song>();
		ArrayList<String> albumList = new ArrayList<String>();
		ArrayList<String> artistList = new ArrayList<String>();

		System.out.println("********************************");
		System.out.println("*         Program Menu         *");
		System.out.println("********************************");
		System.out.println("(L)oad catalog");
		System.out.println("(S)earch catalog");
		System.out.println("(A)nalyze catalog");
		System.out.println("(P)rint catalog");
		System.out.println("(Q)uit");
		System.out.print("\nPlease enter a command (press 'm' for Menu):");

		menuTitle = kbd.next();
		String Delimiter = ",";
		while (!menuTitle.equalsIgnoreCase("q")) {
			switch(menuTitle.toLowerCase()) {
			case "l":
				System.out.print("Load Catalog...");
				System.out.print("Please enter filename: ");
				File oneSongFile = new File(kbd.next());
				try {

					Scanner listFileScan = new Scanner(oneSongFile);

					while(listFileScan.hasNextLine())
					{
						String listLine = listFileScan.nextLine();
						Scanner listLineScan = new Scanner(listLine);
						listLineScan.useDelimiter(Delimiter);
						String artistName = listLineScan.next();
						String albumName = listLineScan.next();
						String titleName = listLineScan.next();
						String durationNumber = listLineScan.next();
						int durationSeconds = Integer.parseInt(durationNumber);
						Song artist = new Song(titleName, artistName, albumName, durationSeconds);
						songList.add(artist);
					}
					System.out.println("Succesfully loaded " + songList.size() + " songs!");
					listFileScan.close();

				}
				catch (FileNotFoundException e) {
					System.out.println("Unable to open file: " + oneSongFile.getName());
				}
				System.out.print("\nPlease enter a command (press 'm' for menu):");
				menuTitle = kbd.next();
				break;
			case "s":
				sResults.clear();
				System.out.print("Search Catalog...");
				System.out.print("\nPlease enter the search query:");
				String sTitle =  kbd.next().toLowerCase();
				int index = 0;
				for (int i=0; i< songList.size(); i++) {
					if (songList.get(i).getTitle().toLowerCase().contains(sTitle)) {

						sResults.add(songList.get(i));
					}			
				}
				if (sResults.size() == 0)
				{
					System.out.println("\n No song found by title: " + sTitle + "!\n");
				}
				else
				{
					System.out.println("\n Found: " + sResults.size() + "\nMatches");
					System.out.println("---------------------------------");
					for(Song resultSongP: sResults) {
						System.out.println(resultSongP);
					}
				}
			}
			System.out.print("\nPlease enter a command (press 'm' for Menu): ");
			menuTitle = kbd.next().toLowerCase();
			break;
			case "a":
				System.out.print("Analyze Catalog...");
				int tPlayTime = 0;
				for (int i = 0; i < songList.size(); i++) 
				{
					String artist = songList.get(i).getArtist();
					if(!artistList.contains(artist))
					{
						artistList.add(artist);
					}
					String album = songList.get(i).getAlbum();
					if(!albumList.contains(album))
					{
						albumList.add(album);
					}
					tPlayTime += songList.get(i).getPlayTime();
				}
				System.out.println("\nArtist: " + albumList.size());
				System.out.println("\nArtist: " + artistList.size());
				System.out.println("Songs: " + songList.size());
				System.out.println("Playtime: " + tPlayTime);
				System.out.println("---------------------------------");

				sResults.clear();
				System.out.print("\nPlease enter a command (press 'm' for Menu): ");
				menuTitle = kbd.next();
				break;
				System.out.print("\nPlease enter a command (press 'm' for Menu): ");
				menuTitle = kbd.next().toLowerCase();
				case "p":
					System.out.println("Succesfully loaded " + songList.size() + " song!");
					System.out.println("---------------------------------");
					for(Song listSongPrint: songList)
					{
						System.out.println(listSongPrint);

					}
					System.out.print("\nPlease enter a command (press 'm' for Menu):");
					menuTitle = kbd.next();
					break;

				case "m":
					System.out.println("********************************");
					System.out.println("*         Program Menu         *");
					System.out.println("********************************");
					System.out.println("(L)oad catalog");
					System.out.println("(S)earch catalog");
					System.out.println("(A)nalyze catalog");
					System.out.println("(P)rint catalog");
					System.out.println("(Q)uit");
					System.out.print("\nPlease enter a command (press 'm' for Menu):");

				case "q":
					System.out.println("Goodbye!");
				}		
			}
		}
	}
}
