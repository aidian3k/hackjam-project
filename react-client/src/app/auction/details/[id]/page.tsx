"use client";
import { Box, Button, Container, Grid, TextField } from "@mui/material";
import ProductInfo from "@/components/product-info/productInfo";
import BidInput from "@/components/bid-input/bidInput";
import ProductQuestions from "@/components/product-questions/productQuestions";
import AuctionInfo from "@/components/auction-info/auctionInfo";
import PersonaLiveComponent from "@/components/persona-live-component/personaLiveComponent";
import { useEffect, useState } from "react";
import { getAuctionById } from "@/services/auction-service";
import { MainPageAuctionDto } from "@/types/auctionTypes";

export default function AuctionDetails({ params }: { params: { id: number } }) {
  const [auctionDetails, setAuctionDetails] =
    useState<MainPageAuctionDto | null>(null);
  const bidders = [
    { name: "Jhon Smith", price: 250 },
    { name: "Lucas Tekk", price: 200 },
    { name: "Monica Rose", price: 120 },
  ];

  // const auction = {
  //   id: 1,
  //   imageUrl: "/img/mocks/20231118_095333.jpg",
  //   title: "Asus VivoBook 15 OLED",
  //   description:
  //     "Experience the future of visual excellence with the Asus VivoBook 15 OLED. Immerse yourself in stunning colors and sharp details on the vibrant OLED display, while enjoying seamless performance powered by cutting-edge technology. The sleek design adds a touch of elegance to your computing experience, making the Asus VivoBook 15 OLED the perfect blend of style and substance. Elevate your work and entertainment—discover the brilliance within each frame.",
  //   tags: ["AsusVivoBook", "#OLEDDisplay"],
  // }

  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    const intervalId = setInterval(() => setRefresh(!refresh), 1000);
    getAuctionById(params.id).then((response) => setAuctionDetails(response));
    return () => clearInterval(intervalId);
  }, [refresh]);

  return (
    <Container component={"main"}>
      <Grid container spacing={2} pt={4} pb={4}>
        <Grid item xs={3}>
          {auctionDetails && <ProductInfo auction={auctionDetails} />}
        </Grid>
        <Grid item xs={6}>
          <PersonaLiveComponent />
        </Grid>
        <Grid item xs={3}>
          {auctionDetails && (
            <AuctionInfo
              currentPrice={auctionDetails.biggestBid}
              numberOfBidders={auctionDetails.bidAuctionInfo.numberOfBidders}
              topBidders={bidders}
              endDate={new Date("2023-12-20T12:01:04.753Z")}
            />
          )}
        </Grid>
      </Grid>
      <BidInput />
      <Box mt={2}>
        <ProductQuestions />
      </Box>
    </Container>
  );
}
