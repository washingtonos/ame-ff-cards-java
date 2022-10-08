package com.fastforward.cardsapi.repository.impl;

import com.fastforward.cardsapi.exception.ApplicationException;
import com.fastforward.cardsapi.model.Card;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.repository.CardRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepositoryImpl implements CardRepository {

  private static final Logger LOG = LogManager.getLogger(CardRepositoryImpl.class);

  private final ConnectionFactory connectionFactory;

  @Autowired
  public CardRepositoryImpl(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Override
  public Optional<Card> findByID(int id) {

    String query = "SELECT * FROM card WHERE id = ?";

    try(Connection connection = connectionFactory.getConnection()){
      try(PreparedStatement statement = connection.prepareStatement(query)){
        statement.setInt(1, id);
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if(resultSet.next()){
          Card card = new Card();
          CardOrigin cardOrigin = new CardOrigin();

          card.setId(resultSet.getInt("id"));
          card.setName(resultSet.getString("name"));
          card.setDescription(resultSet.getString("description"));
          card.setImageUrl(resultSet.getString("image_url"));
          card.setStrength(resultSet.getInt("strength"));
          card.setSpeed(resultSet.getInt("speed"));
          card.setSkill(resultSet.getInt("skill"));
          card.setGear(resultSet.getInt("gear"));
          card.setIntellect(resultSet.getInt("intellect"));
          card.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
          card.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

          cardOrigin.setId(resultSet.getInt("origin_id"));
          card.setOrigin(cardOrigin);

          return Optional.of(card);
        }
      }

    } catch (SQLException e) {
      LOG.error("{}", e.getMessage());
      throw new ApplicationException(e.getMessage());
    }

    return Optional.empty();
  }

  @Override
  public Card save(Card card) {

    String insertQuery = "INSERT INTO card (name, description, image_url, strength, speed, skill, gear, intellect, created_at, updated_at, origin_id)" +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    try(Connection connection = connectionFactory.getConnection()) {
      try(PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, card.getName());
        statement.setString(2, card.getDescription());
        statement.setString(3, card.getImageUrl());
        statement.setInt(4, card.getStrength());
        statement.setInt(5, card.getSpeed());
        statement.setInt(6, card.getSkill());
        statement.setInt(7, card.getGear());
        statement.setInt(8, card.getIntellect());
        statement.setTimestamp(9, Timestamp.valueOf(card.getCreatedAt()));
        statement.setTimestamp(10, Timestamp.valueOf(card.getUpdatedAt()));
        statement.setNull(11, Types.INTEGER);

        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
          card.setId(resultSet.getInt(1));

          return card;
        }
      }
    } catch (SQLException e) {
      LOG.error("{}", e.getMessage());
      throw new ApplicationException(e.getMessage());
    }

    throw new ApplicationException("Cloud not sabe card: " + card);
  }
}