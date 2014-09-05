package com.sopovs.moradanen.bouquinist;

import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sopovs.moradanen.bouquinist.domain.Book;
import com.sopovs.moradanen.bouquinist.domain.Circulation;
import com.sopovs.moradanen.bouquinist.domain.Edition;
import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.repositories.BookRepository;
import com.sopovs.moradanen.bouquinist.repositories.CirculationRepository;
import com.sopovs.moradanen.bouquinist.repositories.EditionRepository;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;
import com.sopovs.moradanen.bouquinist.services.BouquinistService;

@Component
@Profile("bootstrap")
public class Bootstrap implements InitializingBean {

	private static final int NUMBER_OF_PERSONS = 150;

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private EditionRepository editionRepository;
	@Autowired
	private CirculationRepository circulationRepository;

	@Autowired
	private BouquinistService bouquinistService;

	@Override
	public void afterPropertiesSet() throws Exception {

		Random r = new Random(228L);

		for (int i = 0; i < NUMBER_OF_PERSONS; i++) {
			Person newPerson = null;
			if (r.nextBoolean()) {
				newPerson = new Person(NAMES[r.nextInt(NAMES.length)], SURNAMES[r.nextInt(SURNAMES.length)]);
			} else {
				newPerson = new Person(NAMES[r.nextInt(NAMES.length)], NAMES[r.nextInt(NAMES.length)],
						SURNAMES[r.nextInt(SURNAMES.length)]);
			}
			personRepository.save(newPerson);
		}

		for (String title : TITLES) {
			Book book = new Book();
			bookRepository.save(book);
			NavigableSet<Person> authors = new TreeSet<>(Person.BY_FIRSTNAME);
			for (int i = 0; i < (r.nextInt(3) + 1); i++) {
				authors.add(bouquinistService.getRandomPerson(r));
			}

			for (int i = 0; i < (r.nextInt(5) + 1); i++) {
				Edition edition = new Edition();
				edition.setBook(book);
				edition.setTitle(title);
				edition.setEditor(bouquinistService.getRandomPerson(r));
				edition.setAuthors(authors);
				edition.setPublishDate(LocalDate.now().minusDays(r.nextInt(3650)));
				editionRepository.save(edition);

				for (int j = 0; j < (r.nextInt(10) + 1); j++) {
					Circulation circulation = new Circulation();
					circulation.setEdition(edition);
					circulation.setNumberOfCopies((r.nextInt(91) + 10) * 1000);
					circulation.setPrintDate(edition.getPublishDate().plusMonths(r.nextInt(20)));
					circulationRepository.save(circulation);
				}
			}

			if (r.nextDouble() > 0.9 && authors.size() > 1) {
				authors.pollLast();
			}
			if (r.nextDouble() > 0.9) {
				authors.add(bouquinistService.getRandomPerson(r));
			}
		}

	}

	private static final String[] NAMES = new String[] { "John", "David", "Malkolm", "Bob", "Alexander", "Robin",
			"Leo", "Frank", "Frensis", "Tom" };
	private static final String[] SURNAMES = new String[] { "Smith", "Johnson", "Davidson", "Williams", "Thompson",
			"Green", "Black", "White" };

	private static final String[] TITLES = new String[] { "Absalom, Absalom!", "A che punto è la notte",
			"At which point is the night", "After Many a Summer Dies the Swan", "Ah, Wilderness!", "Alien Corn",
			"The Alien Corn", "All Passion Spent", "All the King's Men", "Alone on a Wide, Wide Sea",
			"An Acceptable Time", "Antic Hay", "An Evil Cradling", "Arms and the Man", "As I Lay Dying",
			"A Time to Kill", "Behold the Man", "Beneath the Bleeding", "Beyond the Mexique Bay", "Blithe Spirit",
			"Blood's a Rover", "Blue Remembered Earth", "Blue Remembered Hills", "Bonjour Tristesse",
			"Brandy of the Damned", "Bury My Heart at Wounded Knee", "Butter In a Lordly Dish",
			"By Grand Central Station I Sat Down and Wept", "Cabbages and Kings", "Carrion Comfort",
			"A Catskill Eagle", "Clouds of Witness", "A Confederacy of Dunces", "Consider Phlebas",
			"Consider the Lilies", "Cover Her Face", "The Cricket on the Hearth",
			"The Curious Incident of the Dog in the Night-Time", "The Daffodil Sky", "Dance Dance Dance",
			"A Darkling Plain", "Death Be Not Proud", "The Doors of Perception", "Down to a Sunless Sea",
			"Dulce et Decorum Est", "Dying of the Light", "East of Eden", "Ego Dominus Tuus", "Endless Night",
			"Everything is Illuminated", "Eyeless in Gaza", "Fair Stood the Wind for France", "Fame Is the Spur",
			"A Fanatic Heart", "The Far-Distant Oxus", "A Farewell to Arms", "Far From the Madding Crowd",
			"Fear and Trembling", "For a Breath I Tarry", "For Whom the Bell Tolls", "Frequent Hearses",
			"From Here to Eternity", "A Glass of Blessings", "The Glory and the Dream", "The Golden Apples of the Sun",
			"The Golden Bowl", "Gone with the Wind", "The Grapes of Wrath", "Great Work of Time", "The Green Bay Tree",
			"A Handful of Dust", "Have His Carcase", "The Heart Is a Lonely Hunter",
			"The Heart Is Deceitful Above All Things", "His Dark Materials", "The House of Mirth",
			"How Sleep the Brave", "I Know Why the Caged Bird Sings", "I Sing the Body Electric",
			"I Will Fear No Evil", "If I Forget Thee Jerusalem", "If Not Now, When?", "In a Dry Season",
			"In a Glass Darkly", "In Death Ground", "In Dubious Battle", "An Instant In The Wind",
			"It's a Battlefield", "Jacob Have I Loved", "O Jerusalem!", "Jesting Pilate", "The Last Enemy",
			"The Last Temptation", "The Lathe of Heaven", "Let Us Now Praise Famous Men", "Lilies of the Field",
			"This Lime Tree Bower", "The Line of Beauty", "The Little Foxes", "Little Hands Clapping",
			"Look Homeward, Angel", "Look to Windward", "The Man Within", "Many Waters", "A Many-Splendoured Thing",
			"The Mermaids Singing", "The Millstone", "The Mirror Crack'd from Side to Side", "Moab Is My Washpot",
			"The Monkey's Raincoat", "A Monstrous Regiment of Women", "The Moon by Night", "Mother Night",
			"The Moving Finger", "The Moving Toyshop", "Mr Standfast", "Nectar in a Sieve", "The Needle's Eye",
			"Nine Coaches Waiting", "No Country for Old Men", "No Highway", "Noli Me Tangere", "No Longer at Ease",
			"Now Sleeps the Crimson Petal", "Number the Stars", "Of Human Bondage", "Of Mice and Men",
			"Oh! To be in England", "The Other Side of Silence", "The Painted Veil", "Pale Kings and Princes",
			"The Parliament of Man", "Paths of Glory", "A Passage to India", "O Pioneers!", "Postern of Fate",
			"Precious Bane", "The Proper Study", "Quo Vadis", "Recalled to Life", "Ring of Bright Water",
			"The Road Less Traveled", "A Scanner Darkly", "Shall not Perish", "The Skull Beneath the Skin",
			"The Soldier's Art", "Some Buried Caesar", "Specimen Days", "The Stars' Tennis Balls",
			"Stranger in a Strange Land", "Such, Such Were the Joys", "A Summer Bird-Cage", "The Sun Also Rises",
			"Surprised by Joy", "A Swiftly Tilting Planet", "Taming a Sea Horse", "Tender Is the Night",
			"Terrible Swift Sword", "That Good Night", "That Hideous Strength", "Things Fall Apart",
			"This Side of Paradise", "Those Barren Leaves", "Thrones, Dominations", "Tiger! Tiger!", "A Time of Gifts",
			"Time of our Darkness", "Time To Murder And Create", "Tirra Lirra by the River", "To a God Unknown",
			"To Sail Beyond the Sunset", "To Say Nothing of the Dog", "To Your Scattered Bodies Go",
			"The Torment of Others", "Unweaving the Rainbow", "Vanity Fair", "Vile Bodies", "The Violent Bear It Away",
			"Waiting for the Barbarians", "The Waste Land", "The Way of All Flesh", "The Way Through the Woods",
			"The Wealth of Nations", "What's Become of Waring", "When the Green Woods Laugh",
			"Where Angels Fear to Tread", "The Widening Gyre", "Wildfire at Midnight", "The Wind's Twelve Quarters",
			"The Wings of the Dove", "The Wives of Bath", "The World, the Flesh and the Devil",
			"The Yellow Meads of Asphodel" };

}
