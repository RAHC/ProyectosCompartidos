USE [SISE]
GO
/****** Object:  StoredProcedure [dbo].[TIPOINSTITUCION_Add]*/
-- =============================================
-- Author:		R
-- Create date: 16/9/12
-- Description:	SP para agregar tipo de institucion
-- =============================================


CREATE PROCEDURE [dbo].[TIPOINSTITUCION_Add]
    -- Add the parameters for the stored procedure here
    @NOMBTPINST varchar(50),
    @DESCTPINST varchar(200)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;
BEGIN TRY
    -- Insert statements for procedure here
    begin transaction
		insert into TIPOINSTITUCION values (@NOMBTPINST,@DESCTPINST)
	commit transaction
END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
		PRINT ERROR_MESSAGE()
	END CATCH
END