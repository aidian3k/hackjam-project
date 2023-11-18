"use client";

import { Container } from "@mui/material";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import React, { useEffect, useState } from "react";
import GenerateProductDetails from "@/components/generate-product-details/generateProductDetails";
import { AuctionDetailsDto, MediaDto } from "@/types/auctionTypes";
import FinalAuction from "@/components/final-auction/FinalAuction";

export type Media = {
  id: number;
  imageUrl: string;
  extension: string;
  auction: Auction;
};

export type Auction = {
  id: number;
  startDate: Date;
  endDate: Date;
  localization: string;
};

export default function AddAuction() {
  const [media, setMedia] = useState<MediaDto | null>(null);
  const [auctionDetails, setAuctionDetails] =
    useState<AuctionDetailsDto | null>(null);

  useEffect(() => {
    if (!media) return;
  }, [media]);

  return (
    <Container component={"main"} sx={{ pt: 4 }} maxWidth={"sm"}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <GenerateProductDetails
          setAuctionDetails={setAuctionDetails}
          setMedia={setMedia}
        />
        <FinalAuction media={media} auctionDetails={auctionDetails} />
      </LocalizationProvider>
    </Container>
  );
}
