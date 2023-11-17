package hackathon.project.hackjamproject.repository;

import hackathon.project.hackjamproject.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {}
