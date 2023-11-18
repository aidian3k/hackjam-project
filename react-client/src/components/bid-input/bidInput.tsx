"use client";
import { Box, Button, Grid, TextField } from "@mui/material";
import { useState } from "react";
import axios from "axios";

export default function BidInput() {
  const [bidPriceInput, setBidPriceInput] = useState<string>("");

  const handleBidClick = async () => {
    const bidValue = parseFloat(bidPriceInput);
    if (!isNaN(bidValue)) {
      try {
        const response = await axios.put(
          `http://localhost:8080/api/auction/1`,
          { price: bidValue }
        );
        console.log("Bid placed successfully:", response.data);
      } catch (error) {
        console.error("Error placing bid:", error);
      }
    } else {
      console.error("Invalid bid price");
    }
  };

  return (
    <Box>
      <Grid container spacing={2} sx={{ flexGrow: 1 }}>
        <Grid item xs={3}></Grid>
        <Grid item xs={6}>
          <Box
            display={"flex"}
            alignItems={"center"}
            gap={2}
            mt={2}
            sx={{ display: "flex", justifyContent: "center" }}
          >
            <TextField
              id="price"
              label="Price"
              variant="outlined"
              value={bidPriceInput}
              onChange={(e) => setBidPriceInput(e.target.value)}
            />
            <Button
              variant={"contained"}
              size={"large"}
              sx={{ height: "100%" }}
              onClick={handleBidClick}
            >
              BID
            </Button>
          </Box>
          <Box
            display="flex"
            mt={1}
            gap={1}
            sx={{ display: "flex", justifyContent: "center" }}
          >
            <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
              +100$
            </Button>
            <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
              +200$
            </Button>
            <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
              +300$
            </Button>
          </Box>
        </Grid>
        <Grid item xs={3}></Grid>
      </Grid>
    </Box>
  );
}
