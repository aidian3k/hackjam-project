"use client";

import {
  Box,
  Button,
  Container,
  InputAdornment,
  TextField,
} from "@mui/material";
import Typography from "@mui/material/Typography/Typography";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { MuiFileInput } from "mui-file-input";
import React, { ChangeEvent, FormEvent, useState } from "react";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import { DesktopDateTimePicker } from "@mui/x-date-pickers/DesktopDateTimePicker";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { generateAuctionDetails } from "@/services/auction-service";

export default function AddAuction() {
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
    console.log(file, startDate, endDate, localization);
    const formData: FormData = new FormData();
    if (!file || !startDate || !endDate) return;
    formData.append("image", file);
    formData.append("startDate", startDate.toISOString());
    formData.append("endDate", endDate.toISOString());
    formData.append("localization", localization);

    generateAuctionDetails(formData).then((response) => console.log(response));
  };

  return (
    <Container component={"main"} sx={{ pt: 4 }} maxWidth={"sm"}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Box width={"fit-content"} mb={3}>
          <Typography variant={"h5"}>Add auction</Typography>
          <Box sx={{ height: "1px", width: "100%", backgroundColor: "grey" }} />
        </Box>
        <form onSubmit={handleSubmit}>
          <Typography mb={1}>Product image</Typography>
          <MuiFileInput
            variant="outlined"
            value={file}
            onChange={handleChange}
            InputProps={{ startAdornment: <AttachFileIcon /> }}
            title={"Choose product image"}
            placeholder={"Choose product image"}
          />
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
      </LocalizationProvider>
    </Container>
  );
}
