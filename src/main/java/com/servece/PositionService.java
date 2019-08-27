package com.servece;


import com.dao.PositionDAO;
import com.entity.Position;


import java.util.List;

public class PositionService {

    private PositionDAO positionDAO = new PositionDAO();
    public PositionService() {
    }
    public Position findPosition(int id) {
        return this.positionDAO.findById(id);
    }

    public void savePosition(Position position) {
        positionDAO.save(position);
    }

    public void deletePosition(Position position) {
        positionDAO.delete(position);
    }

    public void updatePosition(Position position) {
        positionDAO.update(position);
    }

    public List getAllPosition() {
        return positionDAO.getAllPosition();
    }
}
