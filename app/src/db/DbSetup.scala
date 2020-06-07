package app.db

object dbSetup {

  def createTableIfNotExists(): Long =
    PgService.pgContext.executeAction(
      """
    CREATE TABLE IF NOT EXISTS todo (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    iscompleted BOOLEAN
    );
    INSERT INTO todo (id, name, iscompleted) VALUES
    (1, 'Eat Cheese', 'true'),
    (2, 'Eat Bread', 'true'),
    (3, 'DringMilk', 'false'),
    (4, 'Do some great work', 'true') ON CONFLICT DO NOTHING;
  """
    )

}
