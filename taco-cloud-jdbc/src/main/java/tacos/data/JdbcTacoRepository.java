package tacos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

  private JdbcTemplate jdbc;

  public JdbcTacoRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);
    for (Ingredient ingredient : taco.getIngredients()) {
      saveIngredientToTaco(ingredient, tacoId);
    }

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    taco.setCreatedAt(new Date());
    String INSERT_SQL = "insert into Taco (name, createdAt) values (?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(new PreparedStatementCreator() {

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"ID"});
			ps.setObject(1, taco.getName());
			ps.setObject(2, new Timestamp(taco.getCreatedAt().getTime()));
			return ps;
		}	
	}, keyHolder);
    return keyHolder.getKey().longValue();
  }

  private void saveIngredientToTaco(
          Ingredient ingredient, long tacoId) {
    jdbc.update(
        "insert into Taco_Ingredients (taco, ingredient) " +
        "values (?, ?)",
        tacoId, ingredient.getId());
  }

}
