"use client";

import React, { ChangeEvent, FormEvent, useState } from "react";
import { generateAuctionDetails, saveImage } from "@/services/auction-service";
import { Box, Button, InputAdornment, TextField } from "@mui/material";
import Typography from "@mui/material/Typography/Typography";
import { MuiFileInput } from "mui-file-input";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import { DesktopDateTimePicker } from "@mui/x-date-pickers/DesktopDateTimePicker";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import Image from "next/image";
import { MediaDto } from "@/types/auctionTypes";

export default function GenerateProductDetails({
  setMedia,
  setAuctionDetails,
}: {
  setMedia: any;
  setAuctionDetails: any;
}) {
  const [file, setFile] = useState<File | null>(null);
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [localization, setLocalization] = useState<string>("");

  const handleChange = (newFile: File | null) => {
    if (!newFile) return;
    setFile(newFile);
  };

  const handleStartDateChange = (date: Date | null) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: Date | null) => {
    setEndDate(date);
  };

  const handleLocalizationChange = (event: ChangeEvent<HTMLInputElement>) => {
    setLocalization(event.target.value);
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!file) return;

    const formData: FormData = new FormData();
    formData.append("file", file);
    saveImage(formData).then((media) => {
      setMedia(media);
      generateAuctionDetails(media).then((response) =>
        setAuctionDetails(response)
      );
    });
  };

  return (
    <Box>
      <Box width={"fit-content"} mb={3}>
        <Typography variant={"h5"}>Add auction</Typography>
        <Box sx={{ height: "1px", width: "100%", backgroundColor: "grey" }} />
      </Box>
      <form onSubmit={handleSubmit}>
        <Typography mb={1}>Product image</Typography>
        <Box display={"flex"} alignItems={"center"} gap={1}>
          <MuiFileInput
            variant="outlined"
            value={file}
            onChange={handleChange}
            InputProps={{ startAdornment: <AttachFileIcon /> }}
            title={"Choose product image"}
            placeholder={"Choose product image"}
          />
          {file && (
            <Image
              style={{ borderRadius: 10 }}
              src={file ? URL.createObjectURL(file) : ""}
              width={80}
              height={80}
              alt={"product image"}
            />
          )}
        </Box>
        <Box display={"flex"} flexDirection={"column"} gap={2} mt={2}>
          <Box display={"flex"} width={"100%"} gap={1}>
            <DesktopDateTimePicker
              sx={{ width: "100%" }}
              label={"Start date"}
              value={startDate}
              onChange={handleStartDateChange}
            />
            <DesktopDateTimePicker
              sx={{ width: "100%" }}
              label={"End date"}
              value={endDate}
              onChange={handleEndDateChange}
            />
          </Box>
          <TextField
            label="Localization"
            type="text"
            value={localization}
            onChange={handleLocalizationChange}
            className="mb-4"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <LocationOnIcon />
                </InputAdornment>
              ),
            }}
          />
          <Typography variant={"body2"} sx={{ color: "grey" }}>
            Please click button below to auto generate content based on the
            photo given above.
          </Typography>
          <Button type="submit" variant="contained" color="primary">
            Generate
          </Button>
        </Box>
      </form>
    </Box>
  );
}
