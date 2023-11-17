// components/AuctionInfo.tsx
import React from "react";
import Image from "next/image";
import { Paper, Box, Typography } from "@mui/material";

interface AuctionInfoProps {
  currentPrice: number;
  numberOfBidders: number;
  topBidders: Bidder[];
  endDate: Date;
}

interface Bidder {
  name: string;
  price: number;
}

function formatTimeDifference(timeDifference: number): string {
  const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
  const hours = Math.floor(
    (timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
  );
  const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
  const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
  return `${days}d ${hours}h ${minutes}m ${seconds}s`;
}

const AuctionInfo: React.FC<AuctionInfoProps> = ({
  currentPrice,
  numberOfBidders,
  topBidders,
  endDate,
}) => {
  var timeLeft = endDate.getTime() - new Date().getTime();
  return (
    <Paper elevation={2} sx={{ p: 2 }}>
      <Box>
        <Typography variant="body1" style={{ marginBottom: "8px" }}>
          Time left: {formatTimeDifference(timeLeft)}
        </Typography>
        <Typography variant="body1" style={{ marginBottom: "8px" }}>
          Highest bid: {currentPrice}$
        </Typography>
        <Typography variant="body1" style={{ marginBottom: "8px" }}>
          Bidders: {numberOfBidders}
        </Typography>
        {topBidders.length > 0 && (
          <ul
            style={{
              border: "1px solid #ccc",
              borderRadius: "8px",
              padding: "10px",
            }}
          >
            {topBidders.map((bidder, index) => (
              <li key={index} style={{ display: "flex", alignItems: "center" }}>
                {index + 1}. {bidder.name} - {bidder.price}$
              </li>
            ))}
          </ul>
        )}
      </Box>
    </Paper>
  );
};

export default AuctionInfo;
