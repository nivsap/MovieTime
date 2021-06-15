package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MovieInfoPageController {
	private Movie movie;
	private Screening screeningChosen;
	private ArrayList<Cinema> cinemas;
	private ArrayList<Screening> screenings;
	@SuppressWarnings("unused")
	private ArrayList<Screening> filteredScreenings;
	private int purchaseType;
	private boolean isTavSagol = false;
	private int tavSagolLimit;
	private double seatsLimit;
	private int seatsTaken;
	private Message msg = new Message();
	private String onlyDate;
	private String onlyTime;
	private String date, time;
	private Hall hall;

	@FXML
	private ImageView movieLargeImageSrc;

	@FXML
	private ImageView movieImageSrc;

	@FXML
	private Label moviePopularity;

	@FXML
	private Label movieName;

	@FXML
	private Label movieGenre;

	@FXML
	private Label movieDescription;

	@FXML
	private Label movieDuration;

	@FXML
	private Label movieLaunchDate;

	@FXML
	private Label movieProducers;

	@FXML
	private Label movieMainActors;

	@FXML
	private Label movieNameSecond;

	@FXML
	private Label movieGenreSecond;

	@FXML
	private AnchorPane orderTicketsContainer;

	@FXML
	private ComboBox<String> cinemaCombo;

	@FXML
	private ComboBox<String> dateCombo;

	@FXML
	private ComboBox<String> timeCombo;

	@FXML
	private ComboBox<String> numberOfSeatsCombo;

	@FXML
	private Button orderTicketBtn;

	void InitPageInfo(Movie movie) {
		this.movie = movie;
		purchaseType = PurchaseTypes.TICKET;
		movieImageSrc.setImage(movie.getImage());
		movieLargeImageSrc.setImage(movie.getLargeImage());
		movieName.setText(movie.getName());
		movieGenre.setText(movie.getGenre());
		moviePopularity.setText(movie.getRate().toString());
		movieNameSecond.setText(movie.getName());
		movieGenreSecond.setText(movie.getGenre());
		movieDescription.setText(movie.getDescription());
		movieDescription.setWrapText(true);
		movieProducers.setText(movie.getProducers());
		movieMainActors.setText(movie.getMainActors());
		movieDuration.setText(movie.getDuration());
		movieLaunchDate.setText(movie.getLaunchDate().toString());
		cinemaCombo.getItems().clear();
		dateCombo.getItems().clear();
		timeCombo.getItems().clear();
		numberOfSeatsCombo.getItems().clear();
		numberOfSeatsCombo.setVisible(false);
		getCinemas(this.movie.getId());
	}

	private void getCinemas(int id) {
		EventBus.getDefault().register(this);
		msg.setAction("cinema contained movies");
		msg.setMovieId(id);
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPurchaseType(int type) {
		this.purchaseType = type;
	}

	public int getPurchaseType() {
		return this.purchaseType;
	}

	@Subscribe
	public void onMessageEvent(Message msg) {
		System.out.println("got message in movieInfoPageController");
		if (msg.getAction().equals("cinema contained movies done")) {
			EventBus.getDefault().unregister(this);
			Platform.runLater(() -> {
				cinemaCombo.getItems().clear();
				cinemas = msg.getCinemas();
				for (Cinema cinema : cinemas) {
					if (!cinemaCombo.getItems().contains(cinema.getName()))
						;
					cinemaCombo.getItems().add(cinema.getName());
				}
			});

		}

		if (msg.getAction().equals("screening for movie done")) {
			EventBus.getDefault().unregister(this);
			Platform.runLater(() -> {
				screenings = msg.getScreenings();
				dateCombo.getItems().clear();
				for (Screening screening : screenings) {
					onlyDate = screening.getDate().toString();
					if (!dateCombo.getItems().contains(onlyDate))
						dateCombo.getItems().add(onlyDate);
				}
			});
		}

		if (msg.getAction().equals("done check purple limit")) {
			EventBus.getDefault().unregister(this);
			isTavSagol = msg.getStatus();
			tavSagolLimit = msg.getTavSagolLimit();
			System.out.println(isTavSagol);
			for (Screening screening : screenings) {
				if (screening.getDate().toString().equals(dateCombo.getValue())) {
					onlyTime = screening.getTime().toString();
					timeCombo.getItems().add(onlyTime);
				}

			}
		}
	}

	@FXML
	private void cinemaChosen() {
		EventBus.getDefault().register(this);
		dateCombo.getItems().clear();
		timeCombo.getItems().clear();
		filteredScreenings = screenings;
		int cinemaId = -1;
		if (cinemaCombo.getValue().isEmpty()) {
			return;
		}
		for (Cinema cinema : cinemas) {
			if (cinema.getName().equals(cinemaCombo.getValue())) {
				cinemaId = cinema.getId();
				break;
			}
		}
		msg.setAction("screening for movie");
		msg.setCinemaId(cinemaId);
		msg.setMovieId(movie.getId());
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void dateChosen() {
		EventBus.getDefault().register(this);
		timeCombo.getItems().clear();
		msg.setAction("check purple limit");
		msg.setScreeningDate(screenings.get(0).getDateAndTime());
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (dateCombo.getValue().isEmpty()) {
			return;
		}
		

	}

	@FXML
	void timeChosen() {
		for (Screening screening : screenings) {
			if (screening.getCinema().getName().equals(cinemaCombo.getValue())) {
				date = screening.getDate().toString();
				time = screening.getTime().toString();
				if (date.equals(dateCombo.getValue()) && time.equals(timeCombo.getValue())) {
					screeningChosen = screening;
					break;
				}

			}
		}
		if (screeningChosen == null) {
			System.out.println("Error in movieInfoPage, screeningChosen is null!!");
		}
		hall = screeningChosen.getHall();
		if (isTavSagol) {

			seatsLimit = hall.getRows() * hall.getCols();
			if ((double) tavSagolLimit * 1.2 < seatsLimit) {
				seatsLimit = tavSagolLimit;
			}
			if (seatsLimit > 0.8 * (double) tavSagolLimit) {
				seatsLimit = 0.8 * (double) tavSagolLimit;
			}
			if (seatsLimit <= 0.8 * (double) tavSagolLimit) {
				seatsLimit = seatsLimit / 2;
			}
			seatsTaken = 0;
			for (int i = 0; i < hall.getRows(); i++) {
				for (int j = 0; j < hall.getCols(); j++) {

					if (screeningChosen.getSeats()[i][j] == 1) {
						seatsTaken++;
					}
				}
			}
			for (int i = 1; i + seatsTaken <= seatsLimit; i++) {
				numberOfSeatsCombo.getItems().add(Integer.toString(i));

			}
			numberOfSeatsCombo.setVisible(true);

		}
	}

	@FXML
	void ChooseSeats(ActionEvent event) throws IOException {
		try {
		/*	if (cinemaCombo.getValue().isEmpty() || dateCombo.getValue().isEmpty() || timeCombo.getValue().isEmpty()) {

				JOptionPane.showMessageDialog(null, "You must fill all the fields");
				return;
			}*/
			
			if(isTavSagol && numberOfSeatsCombo.getValue() == null  || isTavSagol && numberOfSeatsCombo.getValue().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You must fill all the fields");
				return;
			}
			if (cinemaCombo.getValue() == null || dateCombo.getValue() == null || timeCombo.getValue() == null) {
				JOptionPane.showMessageDialog(null, "You must fill all the fields");
				return;
			}
			
			if (cinemaCombo.getValue().isEmpty() || dateCombo.getValue().isEmpty() || timeCombo.getValue().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You must fill all the fields");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		App.setWindowTitle(PageTitles.OrderTicketsPage);
		OrderTicketsPageController controller = (OrderTicketsPageController) App.setContent("OrderTicketsPage");

		if (numberOfSeatsCombo.getValue() == null) {
			numberOfSeatsCombo.setValue(Integer.toString(0));
		}
		controller.setPurchaseInfo(purchaseType, screeningChosen, isTavSagol,
				Integer.parseInt(numberOfSeatsCombo.getValue()), seatsLimit, seatsTaken);
		controller.loadMovieInfo();
		controller.loadScreeningInfo();
		controller.loadHallMap();
	}

}
