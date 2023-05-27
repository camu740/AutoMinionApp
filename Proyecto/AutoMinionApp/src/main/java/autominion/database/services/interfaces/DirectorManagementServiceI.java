package autominion.database.services.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Director;

public interface DirectorManagementServiceI {
	public Director insertNewDirector(final Director newDirector);

	public void updateDirector(final Director updatedDirector);

	public void deleteDirector(final Director deletedDirector);

	public List<Director> searchAll();
}
