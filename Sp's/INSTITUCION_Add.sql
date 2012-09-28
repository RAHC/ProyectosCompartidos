USE [SISE]
GO
/****** Object:  StoredProcedure [dbo].[INSTITUCION_Add]*/
-- =============================================
-- Author:		R
-- Create date: 16/9/12
-- Description:	SP para agregar institucion
-- =============================================


CREATE PROCEDURE [dbo].[INSTITUCION_Add]
    -- Add the parameters for the stored procedure here
    @IDUBIC varchar(10),
    @IDTPINST int,
    @NOMBPINST varchar(50),
    @RESPONINST varchar(50),
    @DIRINST varchar(200),
    @TELINSTCONT char(15),
    @LATITUDINST numeric(10,6),
    @LONGITUDINST numeric(10,6),
    @ALTIMETRIAINST numeric(10,6),
    @PTOREFINST varchar(100)
    
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;
BEGIN TRY
    -- Insert statements for procedure here
    begin transaction
		insert into INSTITUCION values (@IDUBIC,@IDTPINST,@NOMBPINST,@RESPONINST,@DIRINST,@TELINSTCONT,@LATITUDINST,@LONGITUDINST,@ALTIMETRIAINST,@PTOREFINST)
	commit transaction
END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
		PRINT ERROR_MESSAGE()
	END CATCH
END